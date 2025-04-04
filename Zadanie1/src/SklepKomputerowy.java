import java.util.List;

public class SklepKomputerowy {

    private Produkt[] produkty;
    private Klient[] klienci;
    private Zamowienie[] zamowienia;
    private int liczbaProduktow;
    private int liczbaKlientow;
    private int liczbaZamowien;

    public Produkt[] getProdukty() {
        return produkty;
    }

    public void setProdukty(Produkt[] produkty) {
        this.produkty = produkty;
    }

    public Klient[] getKlienci() {
        return klienci;
    }

    public void setKlienci(Klient[] klienci) {
        this.klienci = klienci;
    }

    public Zamowienie[] getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(Zamowienie[] zamowienia) {
        this.zamowienia = zamowienia;
    }

    public int getLiczbaProduktow() {
        return liczbaProduktow;
    }

    public void setLiczbaProduktow(int liczbaProduktow) {
        this.liczbaProduktow = liczbaProduktow;
    }

    public int getLiczbaKlientow() {
        return liczbaKlientow;
    }

    public void setLiczbaKlientow(int liczbaKlientow) {
        this.liczbaKlientow = liczbaKlientow;
    }

    public int getLiczbaZamowien() {
        return liczbaZamowien;
    }

    public void setLiczbaZamowien(int liczbaZamowien) {
        this.liczbaZamowien = liczbaZamowien;
    }

    public void dodajProdukt(Produkt p) {
        if (liczbaProduktow < produkty.length) {
            produkty[liczbaProduktow++] = p;
        }
    }

    public void dodajKlienta(Klient k) {
        if (liczbaKlientow < klienci.length) {
            klienci[liczbaKlientow++] = k;
        }
    }

    public Zamowienie utworzZamowienie(Klient klient, Produkt[] produktyZam, int[] ilosciZam) {
        if (produktyZam.length != ilosciZam.length) {
            System.out.println("Błąd: Niezgodność długości tablic produktów i ilości.");
            return null;
        }

        Zamowienie zamowienie = new Zamowienie();
        zamowienie.setId(liczbaZamowien + 1);
        zamowienie.setKlient(klient);
        zamowienie.setStatus("Złożone");
        zamowienie.setProdukty(produktyZam);
        zamowienie.setIlosci(ilosciZam);

        if (liczbaZamowien < zamowienia.length) {
            zamowienia[liczbaZamowien++] = zamowienie;
            aktualizujStanMagazynowy(zamowienie);
        } else {
            System.out.println("Nie można dodać więcej zamówień.");
        }
        return zamowienie;
    }

    public void aktualizujStanMagazynowy(Zamowienie zamowienie) {
        List<Produkt> produkty = List.of(zamowienie.getProdukty());
        int[] ilosci = zamowienie.getIlosci();

        for (int i = 0; i < produkty.size(); i++) {
            Produkt produkt = produkty.get(i);
            int ilosc = ilosci[i];
            produkt.setIloscWMagazynie(produkt.getIloscWMagazynie() - ilosc);
        }
    }

    public void zmienStatusZamowienia(int idZamowienia, String nowyStatus) {
        for (int i = 0; i < liczbaZamowien; i++) {
            if (zamowienia[i].getId() == idZamowienia) {
                zamowienia[i].setStatus(nowyStatus);
                return;
            }
        }
        System.out.println("Nie znaleziono zamówienia o ID: " + idZamowienia);
    }

    public void wyswietlProduktyWKategorii(String kategoria) {
        for (int i = 0; i < liczbaProduktow; i++) {
            if (produkty[i].getKategoria().equals(kategoria)) {
                produkty[i].wyswietlInformacje();
            }
        }
    }

    public void wyswietlZamowieniaKlienta(int idKlienta) {
        for (int i = 0; i < liczbaZamowien; i++) {
            if (zamowienia[i].getKlient().getId() == idKlienta) {
                zamowienia[i].wyswietlSzczegoly();
            }
        }
    }
}

