class KnuthMorrisPratt:
    def __init__(self, pattern, text):
        self.__pattern = pattern
        self.__text = text
        self.__prefixes = [None] * len(self.__pattern)

    def __set_prefixes(self):
        m = len(self.__prefixes)
        self.__prefixes[0] = 0
        i = 1
        k = 0
        while i < m:
            if self.__pattern[i] == self.__pattern[k]:
                k += 1
                self.__prefixes[i] = k
                i += 1
            else:
                if k != 0:
                    k = self.__prefixes[k - 1]
                else:
                    self.__prefixes[i] = 0
                    i += 1

    def find_pattern(self):
        self.__set_prefixes()
        result = []
        n = len(self.__text)
        m = len(self.__pattern)
        i = 0
        j = 0
        while i < n:
            if self.__pattern[j] == self.__text[i]:
                i += 1
                j += 1

            if j == m:
                result.append(i - j)
                j = self.__prefixes[j - 1]
            elif i < n and self.__pattern[j] != self.__text[i]:
                if j != 0:
                    j = self.__prefixes[j - 1]
                else:
                    i += 1

        return result
