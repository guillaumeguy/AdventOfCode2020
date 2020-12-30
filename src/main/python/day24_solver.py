import math
import cmath
import re 
from collections import defaultdict 
#import matplotlib.pyplot as plt 

##################################
############ PART 1 ##############
##################################
PI = math.pi

moves = {
    'nw' : (1,2 * PI/3),
    'ne' : (1, PI/3),
    'sw' : (1, 4 * PI /3),
    'se' : (1, 5 * PI / 3),
    'e' : (1, 0),
    'w' : (1, PI)
}


polar2z = lambda r,θ: r * cmath.exp( 1j * θ )

def to_complex(tuple):
    return polar2z(tuple[0],tuple[1])


to_complex(moves['nw'])
    

def parse_line(line):
    #line = 'sesenwnenenewseeswwswswwnenewsewsw'
    acc = {}
    for i in moves.keys():
        acc[i] = len(re.findall(i,line))
        line = re.sub(i,'',line)
    return acc

def roundComplex(com,n=3):
    c = complex(round(com.real,n), round(com.imag,n))
    return c

def flip_tile(traject):
    pos = complex(0+0j)
    for (m,cnt) in traject.items():
        print(m,cnt)
        pos+= cnt * to_complex(moves[m])
    return pos


lines = '''sesenwnenenewseeswwswswwnenewsewsw
neeenesenwnwwswnenewnwwsewnenwseswesw
seswneswswsenwwnwse
nwnwneseeswswnenewneswwnewseswneseene
swweswneswnenwsewnwneneseenw
eesenwseswswnenwswnwnwsewwnwsene
sewnenenenesenwsewnenwwwse
wenwwweseeeweswwwnwwe
wsweesenenewnwwnwsenewsenwwsesesenwne
neeswseenwwswnwswswnw
nenwswwsewswnenenewsenwsenwnesesenew
enewnwewneswsewnwswenweswnenwsenwsw
sweneswneswneneenwnewenewwneswswnese
swwesenesewenwneswnwwneseswwne
enesenwswwswneneswsenwnewswseenwsese
wnwnesenesenenwwnenwsewesewsesesew
nenewswnwewswnenesenwnesewesw
eneswnwswnwsenenwnwnwwseeswneewsenese
neswnwewnwnwseenwseesewsenwsweewe
wseweeenwnesenwwwswnew
''' 

lines = '''seweswweswnwseeeesenwneseeseee
wwswwwnewnenwsweneswwwwwseww
eneneeesweeneneeeswnene
nenwswwsenenwnwnenenenenwnesenwnenenenenene
eneeenwneseneeseseseswsewswseswsenwe
eeneneenenwneseneesweneene
wwwwnenenwwwwwswsww
wnwnwenwnwnwnwnenwwwnwnwnwseswnwnenw
sewsweswnwnweswswnwswe
swenwnwnenwnwnweneeswsenwswneswneswsew
seswsweseswneswswneswnwsenwswsenwswsesenw
swsenesewswnenwnewseswseenwnweseseseew
senwseswseswnwswswswseseeseseseswswesw
eeeeneeenweneeeeneesweenewwne
sewnwnwnwnwnwnwnwswnwwnenwnwnwnwnwnwnwe
swenenwneenwwnwwenwwnwwenwseswswnwe
seswseseeseswnwswswse
nenenenwswnenenenenene
wnwseswwwnwwswwswswseswnewwswseww
seeswwnenewwnwswsenewwswwwswswsww
wnesewnwwnwnenwwewsewnwneeseseswwnw
nesewsesesesesesewsweseseseseewnwsw
swswswsewseeswswswswswneswswwsewsesenesw
nesewswwwswswwwwswswwsewnwswnesew
seseesweswnweeswseenwnenweenwwe
swswnweswnwswswswseswseswswswsesesww
nwenenwsenenwnwnenenwneneneneneneneswnesw
eenwewnwsesweesesenwnewnwnwsenesw
wswswswneseseseswse
nwnwnwnwnwenwnwnwwnwsenwnwnenwnwnenew
swsesesewseeseseseseneeseenwesesesese
neseneswsenewsesenwwwswswsenweswnee
swswnwwswsweswswswwswswwwneswwswsw
swswswnewwswswnesweswwswwswnwwwww
neeesewnwnenweswswnwswsenenenwsenwsene
nwnwswnwswenwwwnwwenweenwwnwnww
neswswnwswsenwwswswsweswseswsw
neweeneneseeweeeseeeneswnweene
swenwneswsewswneswwnwswnwswswswswswwe
nenwswneswnenwsenwnenwsew
nwnwnwnenwnwswwnwnwnwneswsenenwnenenwnwnw
wseswsewnewseswswnweseseneswswnenenwse
neeeeeswneneneneneneeneeswnwnwneee
eneswneenwnenwsewswseeeenwseseew
swswswsewseeswenwseenwseswswswseswnwswse
seseeseewseseeseswsesenenwseseseeenee
neswswswswswswnwwswswwswswsewswnesww
wsenenwneneneswnwnwnenenenwnenwnenenwnw
wnwsenesenwwswsewsweenwwwwwswsw
swnwneseswseswnwswsewseswswseswswsweeswsw
seswswswnwneswswwnwswseswnwseswswswenesw
nwnwnwnwnwsenwnwnwwenwnw
nweeeeeeeeeeeswseenewsewne
neneneneseneeesenewwnwenewneneswne
nwwnwnwnwnwnwnwwwwsenwewnw
nwswnenenwneneseneneseenwnwnwnenwne
seneneneneeeenwsenenwsweeneeenenene
wnwwnwwnwswwswwnwnwnenwwwnwnwne
swwenewswwweewswswwswnwswswnwsw
newneswsenenwsenenwswesenwnwnwnenwnenese
neneswneweneneneeneneeeeeneesenw
seweseswsewsenwesenenwsese
newewseneesweswseneswnenewwsenwe
nesenwsewnwnwseswnwswsenenwewwnwsene
nwnewwsenewwnesewwse
swswesweneneneswenwswnenenesenewnee
swnwseenwnewneneseewne
nwwwnwnwenwnwwnwnwnwnw
eseseneswneseswsesenwwwsewnesesesee
nenwnwnwnwnwnwnwnwnwnenenwnwnesewsenesw
nenenenenenwneneswswnenenwwnwneneneenw
seswseswneewneswwseseswseseswswseswswse
wsewneseeseseseseneswsesesenesese
sweswswwswswswswswswswwneesweeww
neswswnwswswswswwswswsweseeswswswewsw
nwneenesenwswenenesenwswnenenewsenene
eweeeeeneeseesenwesweeeeee
wseeenwnweeswnesweeneeenenwseee
esenwseseesenwwseeeeeeeseesee
wwwnewwwswwsewwwewnewwwnwwse
swswseswseswsenwswswnwseswseswesenwese
senwseseseseenwseeseseseseeseswsesese
wewnewwswnwwnewnwswswseewwsww
wswswswswswwswswnwweeswwwwwww
eeenweewenesweeeeeseee
swwswesewswsenewwneswwwswwneeww
nwnwnenenwseneseenenwnenwnenwnenwswnenw
swwswsweeswwswwswwswneswwwnesesesw
nwneswneneneneneenwneneswnenenenenwnenw
nwwwswsewesenwwsweneswnenwseswwsw
sesesesewseneseseseseseesewsewsesesene
nenwneeswswswsewswnwnwwswneswsenew
senenwnwswnwswesewswnwnwnwnweenwneneswse
esewweeeeesenwneeseesweeeene
newwseseeseeseseseseseenwewsesese
seneseeeswweeesewneseseewseene
swseeseseeseswsewnweneswnwenesewnwse
eweneeewesenwneeneenewenesesw
wswnwneseswswnenenenwenenenewneneenw
seeneswsesewseseswnesenwseswswnwsesese
seewesewewsenewneneneswsenwesese
senwnwnwsewnwnwnwswnewnwnwenwnenenwswnwnw
swnwnwnenwwwsenwnenwnwnwnwenwwesewnwnw
seswswneswswswswswswwswneseswswwswswswsw
neseneswnenwwnenenwnwwnesenwnenwnenene
nwseseswswwswsewseswenwnwweeswesese
seneswswswseenweewswnewswwswnwswswne
sesenwswneeenwswseswswneewnenwneswe
ewewwwwwswwnwswwwnwewwww
swnenewesewwwswwsewwnwswwnwnene
wswnwsenwnwswenwnwenwnweswwwneee
weenewswswewnwnweesewnwsweswwsw
eeseeeseeeweeeseneeee
wnwenwnwnwenwnwnwnwnwswweneneswswnee
seseseswsewnwsewseeesenwseseswesese
weweesweeneeneeseeeweeseee
swseneesweenwneswnenwneewwwswswsw
nesewwenesenenwneneeneneneese
esesenewnwnweseseweeeeeeeswsw
nwswwwneswswseeswswnwswswnewsenwswesw
weeeseeseseseesesewnesenweeese
sewsesewnesesesesewneseeenesenwesese
swsesesenesenwsesewseseswseswseseswswse
neswswswwswswswswwswswswswswsw
nwnwnwnwnwnenwnwnwnwsenwnewnenw
seneneenwnwnwneswnwnwnenwnwnwnwnwnwnenw
swnweeeeeeeesew
neseswwnweeeeswseeseeeseeeswene
eeneeeseeneeseeeeewneeenwsw
senwswseesesesesweeenwseee
wwwwswwwsewswswswnewwswneswwsew
eseeneseeseswwseeweeeeneeee
wwnewwwwwwswwswwwewwwnewsew
sewwneeeeeesweeswenesesweneee
nenwnwenwnwswnwnwwswnww
swswseneswswesesenwsesewseswsesesesesenw
seeseseenwweseseseneeseeeeseese
newnenwseswwnwwwwnw
swwnewewwwwwwwwsewwwnwwwnw
swwnwnwnwswenwnwneenw
wnwnwswnenwnwweswweseswwswwewsw
nwenwnwnenwswewswnwnwenwnwnwwnwnenw
esewseseseeeeneseseeeewsenesw
swwwswwnwwnwwweswswsewwswwnesw
senwswnenwnwneenwswnwnwneenwnwnwnenwnww
wwnwwwwwwnewewwwwwsenwswsww
nwnwnwnwnwsewnwnwnwnwnw
swnweseswseseswenwwseneseneswnweswswsw
enenenewseseneseeeneneeneenenwnwne
neneneewwenesenenenenwneneneneneene
neeeneeeneneweeeneee
neseeswswswnwswwswswwswswnwswwswswsw
wwwnenewwnweseseswsenewnwnwnwwse
swwwwsewswswswswswswswwsweneswsw
seneseeseswsesesesesewseseseswsenw
nwwwwnwnwwnwsenwwwwww
wwwwwswnewewseneseswnewswwseewnw
neswnweeswneewwneeeeeneneenenese
neneeneewewsesewnwseeene
seneneneenwnwnwnwnenwnwnenwnwnenwnwswwe
wswswwwseswneswwswwsw
nenwnwesewneneewwwseswwsw
wnewnwwwwwnwnwewwwwswewseww
seeeeseenwsenweswsesesesenwseseee
nenwwsenenwnwnenwnenwnenenesenwnwnwnwnw
wwnwsewwswnewwswewwwwwwsww
seseseeeeseesenwseseseseseese
nweseseseswenesesesesewseseseseseneese
nwswswswwswsweswwsweswswswswswneswsw
wwwsewwwsewwwwwnwwenewnww
nwwnwwwwnwwsesenenwnwwwnwnwwwnw
newnewsewswneenesenenenenenwnesenwe
wnwswswnwwenwnweenwnwewnw
newnenesweneseenenenee
nesweneneneneseewneweeweewswee
wwwwswwwwwwwewww
nwnwsweswwnwnewnwnwnwnenwswwnwneswnw
esenesewswswswswswseswseseswswsw
nwnenenwwnesenenesweneswnenenenesenene
swwewnwwnewwwweswnwsewwnwnenw
swwwneseweseswwswneneswneneseswswnesw
nenwwneneneswnenenenenwnenenenesenw
swswswswseswswnwswseswswsw
swswswsweseswswswwswswswswneswswwsww
neseswseswseswswneneswswsewwswneswswsw
swwswwswwwswnwwwwwwswesww
swswsesewnwswswswseseseswswswne
nwseswswswswswseswseswse
sesewwsesewseesenesenwwseseesesesee
swnwwswswswsweswswwswswswswswswsw
nwnenenwnwnwsenenwwnwsenwseewnwesenww
wwswnwwwwenwwwwwnwsenewewwne
wsenesenwwnesenesenww
eeeeeswnwseseeeseneswenwsewswsee
swwwewwwewwswwwswnw
eeneswnweeswseeenwseeswnwweseee
nenwnwswneenenenwwnenwswneneneneneeenwsw
seseseswneesewwsenwseseesese
newnenenenwneseneenewnenenenesenenenenw
nenwswweseeswsenwsenwsewneswseneswnw
eeneewsenweeeneesewee
nwwnwwsewnwwwwwnwnenwsenweww
neneneswnewnenwneneswnenenenenenenwnesene
seseeseseswseesesenwseseswswsesenwsww
seeswnenwswwnenesenenwnwnwnenenenenene
wneneneswnenenwnwwee
newswwwwsewnwseswneenwwseewnewne
swswwwwswneeswwweseswnew
swnwseeseeewesweneeswenwswwwne
eweseeeeeeeeeenweseeeeenw
wwnwwsenewwwwwwnwnewseswswwsew
neeseseeeesesesesesesesenwsewsesew
nwnenwnenenenenwnwnenenenenenwenenesesw
senenenwnenenenenenewneneneene
senwseswwseswswseseneswswswswsenwseee
nenwwnesenenwseswsenewnenwswnenese
nenwnwnwsenwnwnwwwnwnwwwwnwwnw
seseeswsenwneeeesewwsenesenenwsewse
eeseeesenwseesweeewenwsesesee
eswnwnwwsenwnwnwwnwwne
swswsesenwswseswswswesw
nwwnwwnwnwnwnwnwnwse
newsesenwnwsewsenenewwse
swnwnwwsenwnwnenenwnwnenwnw
swswwnewewwsesenewnwseswswswswswsw
eeeneseeeseseeseeeweseese
neneneneneneseswenweseneweenewnwene
nwnwwnwnwnwnwnwnwwnwnwnwnwnwesenwnenw
senenenewswseeenwswesweeneene
nwnwnwenwnwnwnewsenwnwnenwsenenenwnwwnw
nwswswswseswswnwswswseswesenwswswswseseswsw
senwwnesenwswseseswseseswenwswesesee
weeseeeeseenee
nwnenwnwswnwnwnwnweswnenwswsee
wwwnewwwnwewneswneswnwswwsenwnww
wseswneeseseswenwsesenwnenewse
seneenwswnewneneeeeneeeneneneenenwne
sweneweeseeeseeeneewseee
eswseseenwsesesesewseenenwswnwseeswse
swneswwnwwwwwwseswwswwswswwsene
sesenesewswsesenesesesesesesesesesesese
weneweswewseseeeeneeseeenee
seeseeseenesweseesweeeeswneenwe
weseewnweeeswseseeeswswnewsenene
nenesenenenwnwnenesenenenwnenwwnwnwnenesew
nwweeeeeeeeeeseeseee
neswnenenenwnenenenwnwnwnwnenwne
neneenweeeswwseeswswenweweswnenw
wnwnwnwswewnwwwnewwnewnwwsenwnw
seeswseseswnewnwsesesesesesesesenewnwse
wnewsewewwwnwnwweeswwwnwswnw
seeseeseeeeeeeeeeenwweneew
eeeeseenwsweesenwnweeesenwswee
swwswnwseswswswswswswsweneswswswswswsw
nwnenwwnwwnwenwnwnwsenwnwnwnwwnwnww
swnewneneenewnenwswnwsenewnwneesee
wwwwwsewwwwnwnewnwwnenwseswnw
swsewweweswwwswnwwewwnewwwe
wnwenwswswwwwwwwwwwswewwne
nenenenenenenenenenenesesenenwnenwwnenwe
nwnenwenwenwwnwsenwnwnwwsenwnwnwwnwnw
seswnwnenwenwseswwenesenewneswnwnwnene
enenenwswnwnwnwnenenesenwnwsenwwnwnwnw
swsewwsewseenwnwnewnwwwnwnwnewse
seseswnwswswswswwseswne
nwwnwsewnwnwnwnwnwnwnwnwnwenwnwswnwnwe
swnenenesesenenenenwnenenewnesewneenene
seswsewswseseswseswswswnewneseswseswse
nwsewsenwnwnwwnwnwwswnewnenwwsewwwnw
seeeeseneswnwneenenwnenwneesenwe
wwneeenewseeesesesesweeesesese
swwswswsewseswswneneewneeswnwsesewe
nwneswswswsweswseswswswswseswswswnewswsw
nwnwwsenwwnwnwnwnwwwwnwnwenwsenwww
neenwswnwneenwnwwnwnenwnwswnwswnwnwe
nwswnwnwnwwnwswnwnwnwnwswneewnwnwnwnwenw
swswneseswseeeeeeseseneseesenwsewe
neseesesesewseweeweeneswseeeene
swseeswsesewswswseseeswseswnwsenwswnese
swwswwswsewswwswnwswseswwnenwswsww
nwneewnwnwnwnwwnwnwnwnwseswwnenwnwnwse
nwnwenenwnwnwnwwwenenewneswe
eseeneeneneeseneweenweneeewsw
wswswswsewswswnewweswnewwwswwwsw
newnenenwenenenenwnene
swwnwwswwwswwwenwwwewwwswwsw
neneeneneswwneneenenwseneeneneneeenwne
newnwswsweswseseswnwnwswswswseseswnee
eneeneeneweneneneswnweneesenenene
sweeeswnwneeneneenweeeneesw
neswsenwesesesesweseseweseseeeese
nwseswnwnwswneneenwnwnwwwnenesenenwnenw
sweeeeseeneesweseesenwneesweee
wwswneesewnwseseeeeweswesewsww
sesweswswseeswswsenwswswswwseswneswsesww
swneneneeseneenewnew
sesewsesenwnwesesesewseeeseswsesesese
swseswseswswewswnenwswneenwnwneswne
neneneneneneneneswenenenenwsw
weswwnwwwnwwswswnwnwewweeenwnw
wnwwwwwnwwswwnwnwsewnwwnwwwewe
nwwswwswnwneswsewseeswseseswnewnenwnesw
nwnwnweswsenwsenwswnesenwwnwsenwswee
nwwneseswswweseswseswswnenewseswswnww
eeesweneenwneeeneneswesewneswneene
weeneswswewseeeeeeenwneee
nwnwnwenwnwnwnwwwnwsewsewwwnenwnw
neenenwwneneeswseneswnewnene
enwnwnwnwnwnwnwnwnwenwsenwnwwswnwnwnwnwnw
wnwswseseneseseseswsesesewsenweeseew
swnweswnesenesewseneseseseenweeseew
nwseeeseseeseesesenwswneseseweseesw
wwwswwswneswwwwweww
seswwenwseneewneeneeswneneeswnwnew
nwswseswwwneswswswswwnwseswswesweswsw
sewswwwwswswsenwsenewwwwwwnwsww
nenenenenenwswnenenenenenenene
seseneseseesenwswsewseswswseseseswseseese
swwnesewnwseneswnenesweeeneswewne
nwnwnwwwnwwnwwnwnwswewenwnwnwnwnw
nwnwnwswwnwwsesenwnwwnwsenwenweneswnwnw
seswseeswnwseseswseswseswseseswseseswnw
swswsweswswsenewneswswsenwwswswswswe
sweswswswwwswswwnewwswnewswswnesw
weseseswswseseswsese
wnwwnwwwwwwwewwnwnw
neseeeseeswnwwnesee
nenesewswnesenenenwwnwnenweseeswwenw
seseseneswwsewnwweeeewnesesesesese
seswnewnwsenwswseseswsenwseesesesesesesese
neneneswnenwnwnesenenenenwnenesenesenenene
swneswswnewsenesweswseswwseswswswswsesw
nwnwneswsweeeneeneeeneneesenwnenene
senwnwswnwnweswwnwsenwswnwnesenwswwee
eneseswenwneseswneesenwwneeweewwsw
seewswswswenwnwwwnwnwnwwnwnwwnwne
eneenwnewswnwnewsewseneeswnwenesww
nenesenenenenwnesesenesewnenwnenwwnenese
eenesewsweeeweseweeeseneseee
weneeseesenesesesesesesesewesesee
swswwswwwswwwswnwswwnesweswwneswwsw
neswneseneneewnenenwsenwnenewneneenew
neseeswsweswwseeneenenewwnenwene
eeeneeneeeeweeeeee
nwenwwwwnwwwwwwwnw
nwswnwnwnwnwwnwnwnwnwsenwsewenwnwnwnw
swseneneneswnenenwnenenwneneswnenenenee
nenwnenwsenwnwnwwnwnenwnene
eeneeeeneeeeeeenesw
esweeeweseeeneneeenwseeswnwswswne
nenenenwnesenwnenenwnenenenenene
swnwewswwswnwswwswswnesweswswswwse
wenwneneenesweeneneeeeseneenenwe
nwnewnwnwenwswswnwnenwsenwee
wswnwesenwwswswewneswwweweswwnenw
seweswneeswneswneneeneneneneenewnesee
nenwnwnewneeneeeesweneneswneneswne
nenwenenenenenesweneneneeenenewee
nesenwneneneneneneseneswnwnenenenwnewse
wnwwsesewsewwswwneswneswwnewswww
nwnwsenenwnwnwnwnwwwenenwwwwswwww
neeseeeseseeseeseeesw
neneneswnwneneseneswwneseeneewne
nwswsewwswswewwwwwwwwwnwswsw
swswseseneseesenesenwswsesesesese
eenwwseswwwswsweeswswswswswswsese
eswneswswswswswnewwswswswweswenwe
nwseseswseswswseseseseseswnwseseneswsesw
swseeswenwseeeeseseneseseeeesenee
seeseswwwsesewseseseseseeeenwesee
seswswseseseseseseseeswenwswswswnwsesenese
nwnwwnwsenwwnwnwnwwnwnwnw
nenwnwswnwnweswnwnwenenwneeneswnwnenw
neenewneneneneeneenenwewnesenenene
swswwwnwwnenwswsesewsewww
nwnwnwwneeswswnwesenwnwsewnwwenwnwsww
wsewwwnenwsewwwsewwnewnwwsww
neeneneesweswneeewsweneneenenwenee
nwnewneeneneeseswesewenwneswneewe
wswwnewswswswswneswswswwwswsw
nwwwneneswsenewnwneeeswsweneswenwe
wswswswwswnenewewswswswwnwswseswsewsw
newwnwnwnwnenwnweswnenwnenwnwsenenene
nenwnenenenenenenwwnweneswnenenwsesenew
sesenwnewnwnwnwswsweswnwswwwwsewsee
wnwnwsenwnwnwnwnwenenwseeswnenwswnwsesw
nwnwwnwnwnenwsenwnwswnwenwswnwwenwnw
eeeeseseseeeeeweseneweesee
eswesewenweneeneeseseeseeeee
enenewnenenenenesweswnewnesenenenenenene
swnweeenwnenwnwewneswnewwswwenw
sweswseeesweeeeneeseeeeeneesenw
wnwnwswnwnenwnwnwnwnwneswnweswnwnenenw
swswswswswswwswwnwswsweswswewswwsw
nwenwnewwnwswwnwnwesenwsesenewnwnwnw
wwswwwwwwwwsenw
swwnwsewwnewwnwswnwnewwnwnwnwne
eeeesenweeenenenenweeeeswe
enesweswswewswswseswnwswnw
eswseswnwsenwsewesenenewwwseesenwse
sesewwnewwnewwwewwwnwswnwwww
nenenewnenenenenesenenenenenenenene
nenwnenenwnenwnewnwnenesenenwneswnwnwswne
seseeswnwnwseswsesewseeseseesewesenw
swsenewsewswseneseseeeeseswnweenwse
eswwnwwwewwswne
sewseneewswswseswwseeseswewseswnw
wswwwwwsewwwwwwwwwnwsenew
wnwnenenwswwwwewswsewwwesenwe
wweewnwnwsesenwwewnwwwwwsww
neenwneneeswnwnwneseenesene
nenwsenewnwnenwneswnenenwenwwne
neeseneenwneseenwseenewneswneenesew
nwnwnewnwnwwneswenwnwnwwnwsenwsenwwnw
neneweneseneewwswwnwnwsenwnewnenesene
nwnwnwnwnwnwnwnwnwwswenewnwnwnwenwnesw
weseeneeneeeeseeesesenwnwswswese
senenesewnenenwnenenenewnenenenene
wnwsenwnwwsenwnewwnwenwwwnwnwnww
enwnwnwnwnwsweswnwsenwnwwneneswwnwe
eswnenenwneneweneneeneenenenenwswe
nesesewseswseseeseswsesenesesesesenese
swnwneswnenenwswesweeesweewnweenwsw
swnwnwswswswwseneewwneewswwswswsw
ewnwswnenwwwswnwsenwnwnwnwwwwwnewnw
seseseseseeeesenw
nwnwwnwswwenwwnwnwnwnwswnwnwnwenwnwnw
nenwnenenenesenwnwswnwneneswnwenenwneese
wnwwwwswwewewwwwswnewwwsww
nwswsewswwswneswswnewswwswseswnwww
neenewneneneswenwwnewnwnwnenwsesenw
seseesesesesesesesesweswwsenwsesesese
nwewnwswneseseseewsesenwseseseeseese
ewneeeeeeesweewenewse
wnwwwwwwwwnwnwwsenenwwwwenw
wseswswswswwnenenwwewenwswnew
nenwsenenwnwnwnwnwwnwnwnw
wneeneenenenwsesewnewwsenenwswswsw
eeeeenweeswewsweeeeeenwee
wenwseswseswswnwswswswswneswseswseee
nenenwnwwenwnwnwnwnwnwnwnw
wsewwwnwnewwwwwwwwsewwww
wwwwswnwwswwswewenwswwwwnwswwse
seneswwwwewnewwwwswwswwswwwe
nesewswneswswnwwwsewwwwswswwnesw
swewseswnwnwswweseeswwswnwnweswsw
nwnwnwenwnwswwsenewswnwnwnwnweenwnwsw
swenwswswseswswswswswswswswwswseseese
nwseswnwneswwswwnweeswewnwnwwwew
nwseswwnwenwewwnwnwswwnwnwnwnwseww
enweneeeeeeeenwsweesweew
nesenenwnwwwnwwswnwnwnwwnwwse
weneeeseseneeenweeeneneenwewe
senwnwnwnwswneneswnwnwnewnwsenwwnesee
nwneseneswnenenwnwnwwnw
wnwnwwswnwnwnwnwesewnwnwswnwnwwnwnenwe
nwswwswswwseewsweseswneeneseesew
enwwnwwswwswenwwsweenwnwewnwse
seewwwswnwwswwwwswswewwswswwnw
nenwnenwneenwsenenewnwwnenwnwnenwnw
swswseseswswswneswswswswswswsesweswsenwsene
neneneswswneneseneweswnenwseswseswwsw
wwweneswwwwwswsenew
swswswnwseswswswswswswswsw
nwnenesenenwnenwnwnwnw
eeesenwseeswseeeenwneswseeenwsenew
seeeeseseeswsesenweeeewsewsese
swseneswswseneswnwswswseseswseseswwsewsesw
nwswnenenwneenwsenwswnwnenenwnwnwsenenwsw
nwseseeeenwswswnwswese
nweneneenenwsenenenenese
wswswswwwwewwwneswwesewneswwswnw
nwsenwwnenwnenwsenewnwnwnesenenenwnwne
nenwswnwenwseswnenwwnwnwnwnwwenwnenenw
wseswweswenenwswwswseneewwwnew
seeswseseesewenenwswewe
swswwswswsenenwneswseswswswswswswswswsw
wnwsewsenenenwnwsewnwwenenwswnwnwsenw
seneeseeneswsesenweseswweeswsenwswe
nenenenwnwneenewesesenenenenewsenenene
eneeneeeeeeeeseenew'''


def count_black(dictio):
    cnt = 0
    for (k,v) in dictio.items():
        if v:
            cnt+=1 
    return cnt



tracker = defaultdict(lambda :False) 

for line in lines.split('\n'):
    if len(line) > 0:
        pos = roundComplex(flip_tile(parse_line(line)),3)
        tracker[pos] = not tracker[pos]

print(len(tracker))
print(count_black(tracker))


##########################
######## PART 2 ##########
##########################

def count_black_neighbors(pos,d):
    cnt = 0 
    for (direction,incr) in moves.items():
        newPos = roundComplex(pos + to_complex(incr))
        cnt += d[newPos]
    return cnt

def neighbors(pos):
    acc = [pos]
    for (direction,incr) in moves.items():
        newPos = roundComplex(pos + to_complex(incr))
        acc.append(newPos)
    return acc

def both(a, b): return (a and b) or ((not a) and (not b))


def determine_color(point,state):
            is_black = state[point]
            black_cnt = count_black_neighbors(point,state)
            turn_black = False
            if is_black:
                # Black
                #print(n," has ",b ," neighbors")
                if black_cnt == 0 or black_cnt > 2:
                    turn_black = False
                else:
                    turn_black = True
            else:
                #print("WHITE",black_cnt)
                # white
                if  black_cnt ==2:
                    turn_black = True  # Turn black
                else:
                    turn_black = False
            c = 'black' if turn_black else 'white'
            #if both(is_black, c == 'white'):
            return turn_black

def part2(initial_point,N=4):
    results = {}

    new_state = initial_point.copy()

    results[0] = count_black(new_state)
    
    for day in range(1,N+1):
        state = new_state.copy()
        #print("state len=",len(state))
        #print("STARTING DAY", day)
        to_visit = set()
        for (k,v) in state.items():
            if v:
                for n in neighbors(k):
                    to_visit.add(n)
        #print(len(to_visit),"to visit on day",day)

        while len(to_visit) > 0:
            point = to_visit.pop()
            c = determine_color(point,state)
            new_state[point] = c
        results[day] = count_black(new_state)
    return results


results = part2(tracker,100)
for (i,j) in results.items():
    print(i,j)