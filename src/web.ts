import { WebPlugin } from '@capacitor/core';

import type { BubbleBackgroundPlugin } from './definitions';

export class BubbleBackgroundWeb
  extends WebPlugin
  implements BubbleBackgroundPlugin {
  constructor() {
    super({ name: 'BubbleBackground' });
  }

  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
