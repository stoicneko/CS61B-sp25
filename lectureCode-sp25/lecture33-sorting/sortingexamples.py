import time, random, tqdm, copy
def timealgorithm(arrays, algorithm, algoname):
    print(f"Testing sorting algorithm {algoname}")
    newarrays = copy.deepcopy(arrays)
    start = time.time()
    for i in tqdm.tqdm(newarrays):
        algorithm(i)
    newtime = time.time() - start
    print(f"Total time: {newtime} seconds")

def generatearrays(n,arrcount, randomize = True):
    arrays = []
    for i in range(arrcount):
        k = list(range(n))
        if(randomize):
            random.shuffle(k)
        arrays.append(k)
    return arrays

def mergesort(arr):
    if(len(arr)==1):
        return arr
    a = mergesort(arr[:len(arr)//2])
    b = mergesort(arr[len(arr)//2:])
    aptr, bptr, cptr = 0,0,0
    while(cptr<len(arr)):
        usea = False
        if(aptr!=len(a) and (bptr==len(b) or a[aptr]<b[bptr])):
            usea = True
        if(usea):
            arr[cptr] = a[aptr]
            aptr+=1
        else:
            arr[cptr] = b[bptr]
            bptr+=1
        cptr+=1
    return arr
def quicksort(doshuffle,pivot, partition):
    def f(arr):
        def g(arr,start,end):
            if(start>=end):
                return
            k = pivot(arr,start,end)
            if(k!=start):
                arr[k],arr[start] = arr[start],arr[k]
            p = partition(arr,start,end,start)
            g(arr,start,p-1)
            g(arr,p+1,end)
        if(doshuffle):
            random.shuffle(arr)
        g(arr,0,len(arr)-1)
        return arr
    return f
def validate(sortingalgo):
    for i in range(1,1000):
        a = list(range(i))
        random.shuffle(a)
        #print(a)
        k = sortingalgo(a)
        if(k!= list(range(i))):
            print("Error")
            #print(k)
            return False
    print("Success")
    return True
def leftpivot(arr,start,end):
    return start
def scanpartition(arr,start,end,pivot):
    smaller = []
    equal = []
    bigger = []
    for i in range(start,end+1):
        if(arr[i]<arr[pivot]):
            smaller.append(arr[i])
        elif(arr[i]>arr[pivot]):
            bigger.append(arr[i])
        else:
            equal.append(arr[i])
    v = start+len(smaller)
    for i in [smaller,equal,bigger]:
        for j in i:
            arr[start] = j
            start+=1
    return v
def hoarepartition(arr,start,end,pivot):
    i = start+1
    j = end
    k = arr[pivot]
    while(True):
        while(i<=end and arr[i]<k):
            i+=1
        while(j>=start and arr[j]>k):
            j-=1
        if(i>j):
            break
        arr[i],arr[j] = arr[j],arr[i]
    arr[pivot],arr[j] = arr[j],arr[pivot]
    return j
def exactmedianpivot(arr,start,end):
    vals = arr[start:end+1]
    s,e = 0,len(vals)-1
    while(abs(s-e)>1):
        j = hoarepartition(vals,s,e,s)
        if(j>len(vals)//2):
            e = j-1
        else:
            s = j+1
        #print(s,e,vals,arr,start,end)
    return s+start
def exactmedianpivotthetan(arr,start,end):
    return arr.index(select(arr[start:end+1],(end-start+1)//2))
def select(arr,i):
    if(len(arr)<10):
        arr.sort()
        return arr[i]
    meds = []
    for i in range(len(arr)//5):
        meds.append(select(arr[5*i:5*(i+1)],2))
    x = select(meds,len(meds)//2)
    numless = 0
    for j in arr:
        if(j < x):
            numless+=1
    if(numless==i):
        return x
    elif(numless<i):
        return select([j for j in arr if j>x],i-numless-1)
    else:
        return select([j for j in arr if j<x],i)

#quicksort(doshuffle, pivot determination, partition scheme)
quicksortlthrees = quicksort(True,leftpivot,scanpartition)
quicksorthoare = quicksort(True,leftpivot,hoarepartition)
quicksortexact = quicksort(False,exactmedianpivot,hoarepartition)
quicksortexactthetan = quicksort(False,exactmedianpivotthetan,hoarepartition)
#validate(quicksortexactthetan)
#generatearrays(elems in array, number of arrays, randomize order)
arrays = generatearrays(1000,5000, randomize = True)
timealgorithm(arrays, lambda x: x.sort(),"Powersort")
timealgorithm(arrays, mergesort,"Mergesort")
timealgorithm(arrays, quicksortlthrees,"QuicksortL3S")
timealgorithm(arrays, quicksorthoare,"QuicksortHoare")
timealgorithm(arrays, quicksortexact,"QuicksortExactMedian")
timealgorithm(arrays, quicksortexactthetan,"QuicksortPickMedian")
