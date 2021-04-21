# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.


def find_min(m, s):
    lst = [0] * m
    lst[0] = 1
    s -= 1
    i = m - 1
    result = ""
    while s > 0 and i >= 0:
        if s > 9:
            lst[i] += 9
            s -= 9
            i -= 1
        elif 0 < s <= 9:
            lst[i] += s
            s = 0
    for i in range(m):
        result += "{}".format(lst[i])
    return result


def find_max(m, s):
    lst = [0] * m
    i = 0
    result = ""
    while s > 0 and i <= m - 1:
        if s > 9:
            lst[i] += 9
            s -= 9
            i += 1
        elif 0 < s <= 9:
            lst[i] += s
            s = 0
    for i in range(m):
        result += "{}".format(lst[i])
    return result


def main(m, s):
    if s/9 > m:
        print("-1 -1")
    elif s == 0 and m > 1:
        print("-1 -1")
    elif m == 1 and s == 0:
        print("0 0")
    else:
        mi = find_min(m, s)
        ma = find_max(m, s)
        print("{} {}".format(mi, ma))


x = input()

m = int(x.split(" ")[0])
s = int(x.split(" ")[1])

main(m, s)
