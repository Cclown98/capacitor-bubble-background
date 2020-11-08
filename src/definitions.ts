declare module '@capacitor/core' {
  interface PluginRegistry {
    BubbleBackground: BubbleBackgroundPlugin;
  }
}

export interface BubbleBackgroundPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  changeToBubbleMode(options: { value: string }): Promise<{ value: any }>;
  restoreForegroundMode(options: { value: string }): Promise<{ value: any }>;
}
