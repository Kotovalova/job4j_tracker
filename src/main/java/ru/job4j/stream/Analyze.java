package ru.job4j.stream;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс Analyze получает статистику по аттестатам.
 */
public class Analyze {

    /**
     * Метод averageScore вычисляет общий средний балл.
     * @param stream
     * @return общий средний балл.
     */

    public static double averageScore(Stream<Pupil> stream) {
        return stream
                .map(Pupil::getSubjects)
                .flatMap(List::stream)
                .mapToInt(Subject::getScore)
                .average()
                .orElse(0D);

    }

    /**
     * Метод averageScoreBySubject вычисляет средний балл ученика по его предметам.
     * @param stream
     * @return  Возвращает список из объекта Tuple (имя ученика и средний балл).
     */
    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream
                .map(pupil ->
                        new Tuple(pupil.getName(), Analyze.averageScore(
                                List.of(pupil)
                                        .stream()
                                )
                        )
                )
                .collect(Collectors.toList());
    }

    /**
     * Метод averageScoreByPupil вычисляет средний балл по всем предметам для каждого ученика.
     * @param stream
     * @return Возвращает список из объекта Tuple (название предмета и средний балл).
     */
    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream
                .map(Pupil::getSubjects)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(
                        Subject::getName,
                        LinkedHashMap::new,
                        Collectors.averagingDouble(Subject::getScore)))
                .entrySet()
                .stream()
                .map(f -> new Tuple(f.getKey(), f.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Метод bestStudent
     * @param stream
     * @return возвращает лучшего ученика. Лучшим считается ученик с наибольшим баллом по всем предметам.
     */
    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream
                .map(f -> new Tuple(
                        f.getName(),
                        f.getSubjects()
                                .stream()
                                .mapToInt(Subject::getScore)
                                .sum()))
                .max(Comparator.comparingDouble(Tuple::getScore))
                .orElse(null);
    }

    /**
     * Метод bestSubject - возвращает предмет с наибольшим баллом для всех студентов.
     * @param stream
     * @return Возвращает объект Tuple (имя предмета, сумма баллов каждого ученика по этому предмету)
     */
    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream
                .map(Pupil::getSubjects)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(
                        Subject::getName,
                        LinkedHashMap::new,
                        Collectors.summingDouble(Subject::getScore)))
                .entrySet()
                .stream()
                .map(f -> new Tuple(f.getKey(), f.getValue()))
                .max(Comparator.comparingDouble(Tuple::getScore))
                .orElse(null);
    }
}
