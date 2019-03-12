
using System;
using System.Diagnostics;
using System.Runtime.InteropServices;

class ANotSoDarkGame {
  [DllImport("user32.dll")]  
  static extern int SetWindowText(IntPtr hWnd, string text);
  public static void Main() {
    Process myProcess = new Process();
    myProcess.StartInfo.UseShellExecute = false;
    myProcess.StartInfo.FileName = "java";
    myProcess.StartInfo.Arguments = "game.DarkMain";
    myProcess.Start();
  }
}
