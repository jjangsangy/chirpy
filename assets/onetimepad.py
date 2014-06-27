# One-time pad and Random bit generator
# We use urandom for developing purposes

import socket
import sys



def main():

    TCP_IP = '127.0.0.1'
    TCP_PORT = 1234
    BUFFER_SIZE = 1024
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)



class Pad:

    def __init__(self,encryptfile,decryptfile):
        ef = open(efname,'rb')
        df = open(dfname,'rb')
        self.encryptpad = ef.read()
        self.decryptpad = df.read()
        eindex = 0
        dindex = 0

    def encode(self, string):
        encodedstring = string.encode('utf-8')
        barray = bytearray(encodedstring)

        for i in range(len(barray)):
            barray[i] ^= self.encryptpad[i+eindex]
        self.eindex += len(barray)
        return barray

    def decode(self,barray):
        for i in range(len(barray)):
            barray[i] ^= self.decryptpad[i+dindex]
        self.dindex += len(barray)

        string = ''.join([chr(x) for x in list(barray)])
        return string




def getrandom(n):
    with open('/dev/urandom','rb') as devrandom:
        return devrandom.read(n)

def makepad(fname,n):
    with open(fname,'wb') as f:
        f.write(getrandom(n))


if __name__ == '__main__':
    main()
