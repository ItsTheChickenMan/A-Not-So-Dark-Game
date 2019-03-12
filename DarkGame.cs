/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
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
