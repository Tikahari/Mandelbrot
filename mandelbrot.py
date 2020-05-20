from numpy import arange
import matplotlib.pyplot as plt
from tkinter import Tk, Canvas, PhotoImage, mainloop
from math import sin

class Mandelbrot:
  def __init__(self, yl, yh, ys, xl, xh, xs):
    self.s = ""
    self.s_ = []
    self.yl = yl
    self.yh = yh
    self.ys = ys
    self.xl = xl
    self.xh = xh
    self.xs = xs
  def checkSet(self, c):
    z = 0
    for n in range(10):
      z = z*z + c
      if(abs(z) > 2):
        return '.'
    return '*'
  def getSet(self):
    temp = []
    for y in arange(self.yl,self.yh,self.ys):
      for x in arange(self.xl,self.xh,self.xs):
        self.s += self.checkSet(complex(x,y))
        if self.checkSet(complex(x,y)) == '*':
          temp.append(True)
        else:
          temp.append(False)
      self.s += '\n'
      self.s_.append(temp)
      temp = []
  def plot(self):
    t = Tk()
    WIDTH, HEIGHT = 800, 800
    canvas = Canvas(t, width=WIDTH, height=HEIGHT, bg="#000000")
    canvas.pack()
    img = PhotoImage(width=WIDTH, height=HEIGHT)
    canvas.create_image((WIDTH/2, HEIGHT/2), image=img, state="normal")
    for i in range(len(self.s_)):
      for j in range(len(self.s_[i])):
        if self.s_[i][j] == True:
          img.put("#ffffff", (HEIGHT*i // len(self.s_),WIDTH*j // len(self.s_[i])))
    t.mainloop()

if __name__ == '__main__':
  mb = Mandelbrot(-1, 1, 0.05, -2, 2, 0.075)
  mb.getSet()
  mb.plot()