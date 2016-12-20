import os
import shutil
import sys

usage = "usage: massrename directory"
i = 0
if not os.path.isdir(sys.argv[1]):
  print usage
  exit
dir = sys.argv[1]
for file in os.listdir(dir):
  shutil.move(os.path.join(dir,file),os.path.join(dir,"img_" + str(i) +".jpg"))

  i = i + 1
