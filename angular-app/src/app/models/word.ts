export class Word {
    $key: string;
    word: string;
	count: number;

    constructor(word: string, count: number) {
        this.word = word;
		this.count = count;
    }
}