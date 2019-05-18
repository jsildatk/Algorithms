from naive import Naive
from rabin_karp import RabinKarp
from knuth_morris_pratt import KnuthMorrisPratt
from tests import FindingPatternTest
import unittest
import time


def convert_file(file):
    temp = file.read().splitlines()
    return "".join(temp)


def setup_array(files):
    result = []
    for file in files:
        with open(file, "r") as file_to_convert:
            result.append(convert_file(file_to_convert))

    return result


def count_time(start_time):
    return time.time() - start_time


def test(case, naive, rk, kmp):
    print(f"CASE {case}:")
    start_time_n = time.time()
    print(f"NAIVE: {' '.join(map(str, naive.find_pattern()))}")
    end_time_n = count_time(start_time_n)
    start_time_rk = time.time()
    print(f"RABIN-KARP: {' '.join(map(str, rk.find_pattern()))}")
    end_time_rk = count_time(start_time_rk)
    start_time_kmp = time.time()
    print(f"KNUTH-MORRIS-PRATT: {' '.join(map(str, kmp.find_pattern()))}")
    end_time_kmp = count_time(start_time_kmp)
    print("--------------------------------------------------")
    print(f"NAIVE TIME: {end_time_n}\nRABIN-KARP TIME: {end_time_rk}\nKNUTH-MORRIS-PRATT TIME: {end_time_kmp}")
    print(naive.find_pattern() == rk.find_pattern() == kmp.find_pattern())
    print("--------------------------------------------------")


if __name__ == "__main__":
    files_name = ["pattern", "pattern1", "text", "text1"]
    converted_files = setup_array(files_name)
    n = Naive(converted_files[0], converted_files[2])
    n1 = Naive(converted_files[1], converted_files[3])
    r_k = RabinKarp(converted_files[0], converted_files[2], 128, 27077)
    r_k1 = RabinKarp(converted_files[1], converted_files[3], 128, 27077)
    k_m_p = KnuthMorrisPratt(converted_files[0], converted_files[2])
    k_m_p1 = KnuthMorrisPratt(converted_files[1], converted_files[3])
    test(1, n, r_k, k_m_p)
    test(2, n1, r_k1, k_m_p1)
    unittest.main()
