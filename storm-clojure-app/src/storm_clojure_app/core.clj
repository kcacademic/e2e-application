(ns storm-clojure-app.core
  (:import  [backtype.storm StormSubmitter LocalCluster]
            [storm.kafka KafkaConfig KafkaConfig$ZkHosts KafkaConfig$StaticHosts KafkaSpout SpoutConfig StringScheme])
  (:use     [backtype.storm clojure config])
  (:gen-class))

;;(def kafka-hosts (KafkaConfig$StaticHosts/fromHostString ["localhost:9092"] 1))
(def kafka-zk-hosts (new KafkaConfig$ZkHosts "localhost:2181" "/brokers"))

(def ^{:private true
       :doc "kafka spout config definition"}
  spout-config (let [cfg (SpoutConfig. kafka-zk-hosts "twitter" "/kafkastorm" "discovery")]
                 (set! (. cfg scheme) (StringScheme.))
                 ;; to test whether spout
                 ;; works. -1 = start with latest offset, -2 = start
                 ;; with earliest offset, according to
                 ;; http://stackoverflow.com/questions/17807292/kafkaspout-is-not-receiving-anything-from-kafka
                 (.forceStartOffsetTime cfg -2)
                 cfg))

(def kafka-spout (KafkaSpout. spout-config))




(defspout sentence-spout ["sentence"]
  [conf context collector]
  (let [sentences ["a little brown dog"
                   "the man petted the dog"
                   "four score and seven years ago"
                   "an apple a day keeps the doctor away"]]
    (spout
     (nextTuple []
       (Thread/sleep 100)
       (emit-spout! collector [(rand-nth sentences)])
       )
     (ack [id]
        ;; You only need to define this method for reliable spouts
        ;; (such as one that reads off of a queue like Kestrel)
        ;; This is an unreliable spout, so it does nothing here
        ))))

(defbolt split-sentence ["word"] [tuple collector]
  (let [words (.split (.getString tuple 0) " ")]
    (doseq [w words]
      (emit-bolt! collector [w] :anchor tuple))
    (ack! collector tuple)
    ))

(defbolt word-count ["word" "count"] {:prepare true}
  [conf context collector]
  (let [counts (atom {})]
    (bolt
     (execute [tuple]
       (let [word (.getString tuple 0)]
         (swap! counts (partial merge-with +) {word 1})
         (emit-bolt! collector [word (@counts word)] :anchor tuple)
         (ack! collector tuple)
         )))))

(defn mk-topology []

  (topology
   {"1" (spout-spec kafka-spout)}
   {"3" (bolt-spec {"1" :shuffle}
                   split-sentence
                   :p 5)
    "4" (bolt-spec {"3" ["word"]}
                   word-count
                   :p 6)}))

(defn run-local! []
  (let [cluster (LocalCluster.)]
    (.submitTopology cluster "sample-topology" {TOPOLOGY-DEBUG true} (mk-topology))
    (Thread/sleep 10000)
    (.shutdown cluster)))

(defn submit-topology! [name]
  (StormSubmitter/submitTopology
   name
   {TOPOLOGY-DEBUG true
    TOPOLOGY-WORKERS 3}
   (mk-topology)))

(defn -main
  ([]
     (run-local!))
  ([name]
     (submit-topology! name)))
