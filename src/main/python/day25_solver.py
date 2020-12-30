import math

mod = 20201227
subject_number = 7 

card_public_key = 5764801
card_public_key = 1327981

door_public_key = 17807724
door_public_key = 2822615


##################################
############ PART 1 ##############
##################################

def loop_size(card_public_key,subject_number):
	div = math.log(subject_number)
	r = math.log(card_public_key) / div
	i = 0
	while(abs(r - int(r)) > 0.000000003):
		if i % 1000000 ==0:
			print(i)
		if i % 20201227 ==0:
			print(i)
		card_public_key+=mod
		r = math.log(card_public_key) / div
		i += 1
	return r

def encrypt(key,l_size):
	return (int(key)  ** int(l_size)) % mod


loop_size(card_public_key,subject_number) == 8
loop_size(door_public_key,subject_number) == 11

loop_size_fast(card_public_key,subject_number) == 8


# fails
card_loop = loop_size(card_public_key,subject_number)

encrypt(door_public_key,card_loop)

##################################
############ PART 2 ##############
##################################


# Let's speed up the loops
def loop_size_fast(card_public_key,subject_number):
	l = 1
	runner = subject_number
	while runner != card_public_key:
		l += 1
		runner = (runner * subject_number) % mod
	return l



def prime_factors2(n):
    i = 2
    factors = []
    while i * i <= n:
        if n % i:
            i += 1
        else:
            n //= i
            factors.append(i)
    if n > 1:
        factors.append(n)
    return factors


'''
In [53]: prime_factors2(card_public_key)
Out[53]: [509, 2609]

In [54]: prime_factors2(door_public_key)
Out[54]: [5, 564523]

m1 =  5
k1 = 1 
k2 = m1 + k1 - 1 
(7 ** (k2-k1)) % m1
'''


def nullptr(num):
    powers = []
    i = 1
    while i <= num:
        if i & num:
            powers.append(i)
        i <<= 1
    return powers

def compute_power_modulo(base,power):
	'''compute 7^num % mod 
		
		Inspired by Knuth 
	'''
	power2 = [int(math.log(i) / math.log(2)) for i in nullptr(power)]
	tot = 1
	runner = base
	i = 0
	for p in range(power2[-1]+1):
		if p == power2[i]:
			i+= 1
			tot = tot * runner % mod 
		runner = runner**2 % mod
	return tot

# 7^15 = (2^0) * (2^1) * (2^1)
# 15 = (2^0) + (2^1) + (2^2) + (2^3)
# 7^15 = 7^(2^0) * 7^(2^1) * 7^(2^2) * 7^(2^3)
# start a 7
# Multiply by itself
# if in list, multiply rest
 

def encrypt_fast(key,l_size):
	return compute_power_modulo(key,l_size)

assert(compute_power_modulo(7,15) == (7**15 % mod))

card_sub_keys = [loop_size_fast(i,subject_number) for i in prime_factors2(card_public_key)]
door_sub_keys = [loop_size_fast(i,subject_number) for i in prime_factors2(door_public_key)]

encrypt_fast(door_public_key,sum(card_sub_keys))
encrypt_fast(card_public_key,sum(door_sub_keys))



