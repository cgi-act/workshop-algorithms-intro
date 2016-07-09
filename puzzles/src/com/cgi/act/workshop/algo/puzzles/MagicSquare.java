package com.cgi.act.workshop.algo.puzzles;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MagicSquare {
    private final int order;
    private final int nbrOfIntegers;

    public MagicSquare(final int order) {
        if (order <= 2) {
            throw new IllegalArgumentException("order should be greater than 2");
        }
        this.order = order;
        nbrOfIntegers = order * order;
    }

    public void solveUsingExhaustiveSearch() {
        solveUsingExhaustiveSearch(new ArrayList<>());
    }

    private void solveUsingExhaustiveSearch(final List<Integer> chosenNumbers) {
        if (chosenNumbers.size() == nbrOfIntegers) {
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
        for (int i = 1; i <= nbrOfIntegers; i++) {
            numbers.add(i);
        }
        numbers.removeAll(chosenNumbers);
        return numbers;
    }

    private void checkForValidSolution(final List<Integer> candidateSolution) {
        final List<Integer> sums = calculateSums(candidateSolution);
        final boolean allSumsEqual = sums.stream().distinct().count() == 1;
        if (allSumsEqual) {
            System.out.println(candidateSolution);
        }
    }

    private List<Integer> calculateSums(final List<Integer> candidateSolution) {
        final List<Integer> sums = new ArrayList<>(order + order + 2);

        int corSumTopLeftToBottomRight = 0;
        int colSumTopRightToBottomLeft = 0;
        for (int i = 1; i <= order; i++) {
            int sumRow = 0;
            int sumColumn = 0;
            for (int y = 1; y <= order; y++) {
                sumRow += candidateSolution.get(calculateListIndex(i, y));
                sumColumn += candidateSolution.get(calculateListIndex(y, i));
            }
            sums.add(sumRow);
            sums.add(sumColumn);

            corSumTopLeftToBottomRight += candidateSolution.get(calculateListIndex(i, i));
            colSumTopRightToBottomLeft += candidateSolution.get(calculateListIndex(i, order - i + 1));
        }
        sums.add(corSumTopLeftToBottomRight);
        sums.add(colSumTopRightToBottomLeft);

        return sums;
    }

    private int calculateListIndex(final int rowIndex, final int columnIndex) {
        return (rowIndex - 1) * order + (columnIndex - 1);
    }

    public static void main(String[] args) {
        final MagicSquare magicSquare = new MagicSquare(3);
        magicSquare.solveUsingExhaustiveSearch();
    }
}
