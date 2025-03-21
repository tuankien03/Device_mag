import { Injectable } from '@angular/core';
import { Message } from '../model/message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  messages: Message[] = []; // 
s
  addMessage(message: Message): void {
    this.messages.push(message);
    setTimeout(() => {
      this.removeMessage(0);
    }, 1000);
    if (this.messages.length > 8) {
      this.removeMessage(0);
    }
  }

  clear(): void {
    this.messages = [];
  }

  removeMessage(index: number): void {
    this.messages.splice(index, 1);
  }

  constructor() { }
}
