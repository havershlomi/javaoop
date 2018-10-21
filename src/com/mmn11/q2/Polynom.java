package com.mmn11.q2;

import java.util.ArrayList;
import java.util.HashMap;

public class Polynom implements Comparable<Polynom> {

    public ArrayList<PolynomItem> getPolynom() {
        return polynom;
    }

    private ArrayList<PolynomItem> polynom = new ArrayList<PolynomItem>();

    public Polynom(ArrayList<Double> coefficients, ArrayList<Integer> exponents) {
        for (int i = 0; i < coefficients.size(); i++) {
            if (coefficients.get(i) != 0) {
                this.polynom.add(new PolynomItem(exponents.get(i), coefficients.get(i)));
            }
        }

        //sort the polynum
        this.polynom.sort((o1, o2) -> o2.compareTo(o1));
    }

    //add 2 polynoms
    public Polynom plus(Polynom o) {
        return manipulate(this, o, '+');
    }

    // substract 2 polynoms
    public Polynom minus(Polynom o) {
        return manipulate(this, o, '-');
    }

    //manipulate 2 polynoms together
    private Polynom manipulate(Polynom p1, Polynom p2, char action) {
        HashMap<Integer, Double> parts = new HashMap<Integer, Double>();

        for (PolynomItem i : p1.getPolynom()) {
            parts.put(i.getExponent(), i.getCoefficients());
        }

        for (PolynomItem i : p2.getPolynom()) {
            double value = parts.getOrDefault(i.getExponent(), 0.0);

            if (action == '+') {
                parts.put(i.getExponent(), value + i.getCoefficients());

            } else if (action == '-') {
                parts.put(i.getExponent(), value - i.getCoefficients());

            }
        }
        ArrayList<Integer> exponent = new ArrayList<Integer>(parts.keySet());
        ArrayList<Double> coefficient = new ArrayList<Double>(parts.values());
        return new Polynom(coefficient, exponent);
    }

    public Polynom derivative() {
        ArrayList<Integer> exponent = new ArrayList<Integer>();
        ArrayList<Double> coefficients = new ArrayList<Double>();
        int expo = 0;
        double coef = 0;
        for (PolynomItem i : this.getPolynom()) {
            expo = i.getExponent();
            coef = i.getCoefficients();
            if (i.getExponent() != 0) {
                exponent.add(expo - 1);
                coefficients.add(coef * expo);
            }
        }
        return new Polynom(coefficients, exponent);
    }

    @Override
    public String toString() {
        String value = "";
        for (PolynomItem item : this.getPolynom()) {
            double coefficient = item.getCoefficients();

            value += coefficient > 0 ? " +" : "";
            if (coefficient == 1 && item.getExponent() == 0) {
                value += coefficient;
            } else if (coefficient != 1) {
                value += coefficient;
            }
            if (item.getExponent() == 1) {
                value += "x";
            } else if (item.getExponent() > 1) {
                value += "x^" + item.getExponent();
            }
        }
        if (value.charAt(1) == '+') {
            value = value.substring(2);
        }
        return value;
    }

    @Override
    public int compareTo(Polynom o) {
        PolynomItem myFirst = this.getPolynom().get(0);
        PolynomItem oFirst = o.getPolynom().get(0);

        if (myFirst.getExponent() > oFirst.getExponent() || (myFirst.getExponent() == oFirst.getExponent() && myFirst.getCoefficients() > oFirst.getCoefficients()))
            return 1;
        if (myFirst.getExponent() < oFirst.getExponent() || (myFirst.getExponent() == oFirst.getExponent() && myFirst.getCoefficients() < oFirst.getCoefficients()))
            return -1;
        return 0;
    }
}
