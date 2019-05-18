import unittest
from naive import Naive
from rabin_karp import RabinKarp
from knuth_morris_pratt import KnuthMorrisPratt


class FindingPatternTest(unittest.TestCase):
    test_case = ["AABA", "AABAACAADAABAAABAA"]
    test_case1 = ["ASDF", "FGDASFDFSDFDFXZ"]

    def test_naive(self):
        naive_test = Naive(self.test_case[0], self.test_case[1])
        naive_test1 = Naive(self.test_case1[0], self.test_case1[1])
        self.assertEqual(naive_test.find_pattern(), [0, 9, 13])
        self.assertEqual(naive_test1.find_pattern(), [])

    def test_rabin_karp(self):
        rabin_karp_test = RabinKarp(self.test_case[0], self.test_case[1], 128, 27077)
        rabin_karp_test1 = RabinKarp(self.test_case1[0], self.test_case1[1], 128, 27077)
        self.assertEqual(rabin_karp_test.find_pattern(), [0, 9, 13])
        self.assertEqual(rabin_karp_test1.find_pattern(), [])

    def test_knuth_morris_prat(self):
        knuth_morris_prat_test = KnuthMorrisPratt(self.test_case[0], self.test_case[1])
        knuth_morris_prat_test1 = KnuthMorrisPratt(self.test_case1[0], self.test_case1[1])
        self.assertEqual(knuth_morris_prat_test.find_pattern(), [0, 9, 13])
        self.assertEqual(knuth_morris_prat_test1.find_pattern(), [])
