package racingcar.service;

import static racingcar.exception.ExceptionMessage.EMPTY_CARS;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import camp.nextstep.edu.missionutils.Randoms;
import racingcar.domain.Position;

public class RacingCarService {
    private static final int MIN_RANDOM_NUMBER = 0;
    private static final int MAX_RANDOM_NUMBER = 0;
    private static final int STOP_BOUNDARY = 3;
    private Cars cars;

    public RacingCarService(Cars cars){
        this.cars = cars;
    }

    public void moveCars() {
        for (Car car : cars.getCars()) {
            int randomNumber = Randoms.pickNumberInRange(MIN_RANDOM_NUMBER, MAX_RANDOM_NUMBER);
            if (randomNumber >= STOP_BOUNDARY) {
                car.move();
            }
        }
    }

    public List<Car> findWinners() {
        Position longestPosition = getLongestPosition();
        List<Car> winners = cars.getCars()
                .stream()
                .filter(car -> car.isSamePosition(longestPosition))
                .collect(Collectors.toList());
        return winners;
    }

    private Position getLongestPosition() {
        return cars.getCars()
                .stream()
                .map(Car::getPosition)
                .max(Comparator.comparing(Position::getPosition))
                .orElseThrow(() -> new IllegalArgumentException(EMPTY_CARS.getMessage()));
    }
}