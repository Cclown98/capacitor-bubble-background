import type { PluginImplementations } from '@capacitor/core';
import { Plugins, registerPlugin } from '@capacitor/core';

import type { BubbleBackgroundPlugin } from './definitions';
import { BubbleBackgroundWeb } from './web';

const implementations: PluginImplementations<BubbleBackgroundPlugin> = {
  android: Plugins.BubbleBackground,
  ios: Plugins.BubbleBackground,
  web: new BubbleBackgroundWeb(),
};

const BubbleBackground = registerPlugin(
  'BubbleBackground',
  implementations,
).getImplementation();

export { BubbleBackground };
