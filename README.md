# Ex3Client

Note: I changed some of the variables and parameters. While the assignment has the server sending unsigned bytes, because no such data type exists in java like that, I read using "readUnsignedByte()" which returns int. For checksum(), I didn't return short because short contains negatives, effectively halving the size of the data since it is only positive. Thus, I used int for this as well. 

Hopefully the program outputs correctly. If not, it's because of this. 
