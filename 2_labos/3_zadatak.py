def mymax(iterable, key=lambda x: x):
    # incijaliziraj maksimalni element i maksimalni kljuc
    max_x = max_key = None

    # obidi sve elemente
    for x in iterable:
        result_key = key(x)
        if result_key > max_key:
            max_key = result_key
            max_x = x
    # ako je key(x) najveci -> azuriraj max_x i max_key

    # vrati rezultat
    return max_x


maxint = mymax([1, 3, 5, 7, 4, 6, 9, 2, 0])
maxchar = mymax("Suncana strana ulice")
maxstring = mymax([
    "Gle", "malu", "vocku", "poslije", "kise",
    "Puna", "je", "kapi", "pa", "ih", "njise"])
print maxint
print maxchar
print maxstring
D = {'burek': 8, 'buhtla': 5}
maxprice = mymax(D, D.get)
print maxprice
people = [("Marko", "Markic"), ("Ivo", "Ivic"), ("Pero", "Peric"), ("A", "Z")]
maxperson = mymax(people)
print maxperson