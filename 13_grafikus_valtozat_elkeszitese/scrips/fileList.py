import os
import datetime

for entry in os.scandir("."):
    if entry.is_file():
        stat = entry.stat()
        fajlnev = entry.name
        meret   = stat.st_size / 1024
        # Keletkezési idő (Windowsen creation time, Unixon első stat.st_ctime)
        keletkezett = datetime.datetime.fromtimestamp(stat.st_ctime) \
                                     .strftime("%Y-%m-%d %H:%M:%S")
        # Név kiterjesztés előtt
        elotag = os.path.splitext(fajlnev)[0]
        print(f"{fajlnev}\t{meret:.1f} kB\t{keletkezett}")