class Naive:
    def __init__(self, pattern, text):
        self.pattern = pattern
        self.text = text

    def find_pattern(self):
        result = []

        for i in range(len(self.text) - len(self.pattern) + 1):
            j = 0

            for j in range(len(self.pattern)):
                if self.text[i + j] != self.pattern[j]:
                    break

            if j == (len(self.pattern) - 1):
                result.append(i)

        return result
