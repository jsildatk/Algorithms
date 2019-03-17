class Lcs:
    __x = ""
    __y = ""
    __m = 0
    __n = 0
    __length = 0
    __tab = []

    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.m = int(len(self.x))
        self.n = int(len(self.y))
        self.tab = [[0] * (self.n+1) for i in range(self.m+1)]

    def getLength(self):
        for i in range(self.m+1):
            for j in range(self.n+1):
                if i == 0 or j == 0: # edges are 0
                    self.tab[i][j] = 0
                elif self.x[i-1] == self.y[j-1]: # diagonal + 1
                    self.tab[i][j] = self.tab[i-1][j-1]+1 
                else: # get max 
                    self.tab[i][j] = max(self.tab[i-1][j], self.tab[i][j-1]) 

        self.length = self.tab[self.m][self.n] # length of lcs

    def print(self):
        result = [""] * (self.length+1)
        i = self.m 
        j = self.n
        while i > 0 and j > 0:
            if self.x[i-1] == self.y[j-1]: # if chars are the same => is a part of LCS
                result[self.length-1] = self.x[i-1]
                i -= 1
                j -= 1
                self.length -= 1
            elif self.tab[i-1][j] > self.tab[i][j-1]: # if not => find greater and go to it's direction
                i -= 1
            else:
                j -= 1

        return "".join(result)

if __name__ == "__main__":
    test = Lcs("aabca", "cabba")
    test.getLength()
    print(test.print())