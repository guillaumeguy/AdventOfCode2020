input = "32415"
input = "389125467"
input = "158937462"


def final_answer(ls):
    i = ls.index(1)
    return ls[i:len(ls)] + ls[:i]

def part1(input):
    seq = []
    for e in input:
        seq.append(int(e))

    ma = max(seq)
    mi = min(seq)

    for i in range(100):
        print(f"ROUND {i}")
        just_picked_up = seq[1:4]
        dst = seq[0] -1 
        
        if dst == 0:
            dst = ma

        while dst in just_picked_up:
            dst -=1
            print("decrementing")
            if dst == 0:
                dst = ma
            
        d = seq.index(dst)
        print("going to",dst,d)

        MOVE =  seq[1:4]
        SLIM = seq[4:d]
        DST = [seq[d]]
        LAST = seq[d+1:len(seq)]
        
        newSeq = SLIM + DST + MOVE + LAST + [seq[0]]
        #print("SLIM=",SLIM, "DST=" , DST ,"MOVE=", MOVE ,"LAST=", LAST,"MOVED=",[seq[0]])
        #print(newSeq)
        
        seq  = newSeq

    print(final_answer(seq))




class Node:
    def __init__(self,val):
        self.val = val
        self.next = None

class LinkedList:
    def __init__(self,head):
        self.head = head
        self.lastElement = head

    def len(self):
        cnt = 1
        node = self.head
        while node.next is not None:
            node = node.next
            cnt+=1
        return cnt

    def subList(self,f,to):
        ls = []
        node = self.head 
        cnt = 1
        while cnt != f + 1 and node.next is not None:
            node = node.next
            cnt+=1
        
        while cnt != to + 1:
            if node is None:
                raise Exception("Reached end of list")
            ls.append(node.val)
            node = node.next
            cnt+=1

        return ls

    def last(self):
        l = self.lastElement # start from the last milestone
        while l.next is not None:
            l = l.next

        self.lastElement = l
        return l

    def insertAfter(self,e,n,lookup=None):
        ''' Lookup speeds up the lookup process by directly getting to the node instead 
        of traversing the list iteratively'''
        if lookup is None:
            node = self.head
            while e != node.val:
                node = node.next
        else:
            node = lookup[e]
        # save list (returns to main linkedList)
        return_to_list = node.next 
        
        # Insert at after node
        node.next = n 

        while node.next is not None:
            node = node.next 

        # Stich back
        node.next = return_to_list

    def At(self,i):
        node = self.head 
        while i != 0:
            node = node.next
            i-=1
        return node


    def to_list(self):
        node = self.head
        acc = []
        while node.next is not None:
            acc.append(node.val)
            node = node.next 
        # last one
        acc.append(node.val)
        return acc 


####################################
############## PART 2 ##############
####################################

first = Node(int(input[0]))
last = first 
lookup = {}
lookup[0] = first 
for i in range(2,1+10**6):
    if i <= len(input):
        n = Node(int(input[i-1]))
    else:
        n = Node(i)
    lookup[i] = n
    last.next = n 
    last = n 

myList = LinkedList(first)



def part2(myList,N):
    '''
    Too slow ...  
    '''
    for i in range(N):
        print(f"ROUND {i}")
        just_picked_up = myList.subList(1,4)
        head = myList.head
        #print("just_picked_up",just_picked_up)
        dst = myList.head.val -1 
        
        if dst == 0:
            dst = ma

        while dst in just_picked_up:
            dst -=1
            #print("decrementing")
            if dst == 0:
                dst = ma
            

        beg_of_move = myList.At(1)
        end_of_move = myList.At(3)
        
        
        beg_of_slim = myList.At(4)
        
        ## create link between first node and node #4
        myList.head = beg_of_slim
        
        end_of_move.next = None 

        myList.insertAfter(dst,beg_of_move,lookup)

        last = myList.last()
        last.next = head
        head.next = None
    return myList


import cProfile

cProfile.run('part2(myList,100)')

