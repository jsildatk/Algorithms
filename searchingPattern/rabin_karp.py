class RabinKarp:
    def __init__(self, pattern, text, d, q):
        self.pattern = pattern
        self.text = text
        self.d = d
        self.q = q

    def find_pattern(self):
        result = []
        m = len(self.pattern)
        n = len(self.text)
        h = 1
        p = 0
        t = 0
        j = 0

        for i in range(m - 1):
            h = (h * self.d) % self.q  # h = (d ^ (m-1)) % q

        for i in range(m):
            p = (self.d * p + ord(self.pattern[i])) % self.q
            t = (self.d * t + ord(self.text[i])) % self.q

        for i in range(n - m + 1):
            if p == t:
                for j in range(m):
                    if self.text[i + j] != self.pattern[j]:
                        break

                j += 1
                if j == m:
                    result.append(i)

            if i < n - m:
                t = (self.d * (t - ord(self.text[i]) * h) + ord(self.text[i + m])) % self.q

        return result
