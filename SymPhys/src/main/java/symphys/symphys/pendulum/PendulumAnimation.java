package symphys.symphys.pendulum;

import javafx.animation.*;

public class PendulumAnimation extends AnimationTimer{
    long startTime=0;
    long pauseTime=0;
    long lastStop=0;
    Pendulum pendulum;
    PendulumAnimation(Pendulum pendulum){
        this.pendulum=pendulum;
    }

    @Override
    public void start() {
        if(pauseTime==0)
            startTime=System.nanoTime();
        pauseTime+=System.nanoTime()-lastStop;
        super.start();
    }

    @Override
    public void stop() {
        lastStop=System.nanoTime();
        super.stop();
    }

    @Override
    public void handle(long nanoseconds) {
        long currentTime=nanoseconds-pauseTime;
        double time = (double) currentTime/100000000;
        pendulum.body.adjustPosition(pendulum.calculator.position(time));
    }
}
