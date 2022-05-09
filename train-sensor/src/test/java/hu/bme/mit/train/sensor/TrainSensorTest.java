package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainSensorImpl trainSensor;
    TrainUser user;
    TrainController controller;

    @Before
    public void before() {
        user = mock(TrainUser.class);
        controller = mock(TrainController.class);
        trainSensor = new TrainSensorImpl(controller, user);

    }

    @Test
    public void tooHighSpeedLimit() {
        trainSensor.overrideSpeedLimit(530);
        verify(user, times(1)).setAlarmState(true);

    }

    @Test
    public void tooLowSpeedLimit() {
        trainSensor.overrideSpeedLimit(-5);
        verify(user, times(1)).setAlarmState(true);

    }

    @Test
    public void tooBigSpeedLimitChange() {
        when(controller.getReferenceSpeed()).thenReturn(50);
        trainSensor.overrideSpeedLimit(24);
        verify(user, times(1)).setAlarmState(true);

    }

    @Test
    public void noNeedToSetAlarm() {
        when(controller.getReferenceSpeed()).thenReturn(50);
        trainSensor.overrideSpeedLimit(30);
        verify(user, times(0)).setAlarmState(true);

    }
}
