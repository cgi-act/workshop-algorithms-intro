package com.cgi.act.workshop.algo.puzzles;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MagicSquare {
    public static void main(String[] args) {
        final MagicSquare magicSquare = new MagicSquare();
        magicSquare.solveUsingExhaustiveSearch();
    }

    public void solveUsingExhaustiveSearch() {
        solveUsingExhaustiveSearch(new ArrayList<>());
    }

    private void solveUsingExhaustiveSearch(final List<Integer> chosenNumbers) {
        if (chosenNumbers.size() == 9) {
            checkForValidSolution(chosenNumbers);
            return;
        }

        final Set<Integer> numbers = buildNumbers(chosenNumbers);
        numbers.forEach(number -> {
            chosenNumbers.add(number);
            solveUsingExhaustiveSearch(chosenNumbers);
            chosenNumbers.remove(chosenNumbers.size() - 1);
        });
    }

    private Set<Integer> buildNumbers(final List<Integer> chosenNumbers) {
        final Set<Integer> numbers = new LinkedHashSet<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        numbers.removeAll(chosenNumbers);
        return numbers;
    }

    private void checkForValidSolution(final List<Integer> candidateSolution) {
        // calculate sum of each row
        final int sumRow1 = candidateSolution.get(0) + candidateSolution.get(1) + candidateSolution.get(2);
        final int sumRow2 = candidateSolution.get(3) + candidateSolution.get(4) + candidateSolution.get(5);
        final int sumRow3 = candidateSolution.get(6) + candidateSolution.get(7) + candidateSolution.get(8);

        // calculate sum of each column
        final int sumCol1 = candidateSolution.get(0) + candidateSolution.get(3) + candidateSolution.get(6);
        final int sumCol2 = candidateSolution.get(1) + candidateSolution.get(4) + candidateSolution.get(7);
        final int sumCol3 = candidateSolution.get(2) + candidateSolution.get(5) + candidateSolution.get(8);

        // calculate sum of each corner-to-corner
        final int sumCor1 = candidateSolution.get(0) + candidateSolution.get(4) + candidateSolution.get(8);
        final int sumCor2 = candidateSolution.get(2) + candidateSolution.get(4) + candidateSolution.get(6);

        if (isValid(sumRow1, sumRow2, sumRow3, sumCol1, sumCol2, sumCol3, sumCor1, sumCor2)) {
            System.out.println(candidateSolution);
        }
    }

    private boolean isValid(Integer... sums) {
        final int sum = sums[0];
        for (int i = 1; i < sums.length; i++) {
            if (sum != sums[i]) {
                return false;
            }
        }
        return true;
    }
}
