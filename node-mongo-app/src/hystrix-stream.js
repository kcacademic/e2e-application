var hystrixSSEStream = require('hystrixjs').hystrixSSEStream

exports.index = function hystrixStreamResponse(request, response) {

    response.append('Content-Type', 'text/event-stream;charset=UTF-8')
    response.append('Cache-Control', 'no-cache, no-store, max-age=0, must-revalidate')
    response.append('Pragma', 'no-cache')

    return hystrixSSEStream.toObservable().subscribe(
        function onNext(sseData) {
            response.write('data: ' + sseData + '\n\n')
        },
        function onError(error) {
            console.log(error)
        },
        function onComplete() {
            return response.end()
        }
    );
};