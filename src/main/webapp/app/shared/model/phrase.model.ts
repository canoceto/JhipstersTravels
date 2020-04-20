export interface IPhrase {
  id?: number;
  author?: string;
  text?: string;
  referense?: string;
}

export class Phrase implements IPhrase {
  constructor(public id?: number, public author?: string, public text?: string, public referense?: string) {}
}
