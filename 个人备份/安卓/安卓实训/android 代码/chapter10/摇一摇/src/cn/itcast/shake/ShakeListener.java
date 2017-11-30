package cn.itcast.shake;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

/**
 * һ������ֻ�ҡ�εļ�����
 */
public class ShakeListener implements SensorEventListener {
	private static final int SPEED_SHRESHOLD = 2000; // �ٶ���ֵ����ҡ���ٶȴﵽ��ֵ���������
	private static final int UPTATE_INTERVAL_TIME = 70; // ���μ���ʱ����
	private SensorManager sensorManager; // ������������
	private Sensor sensor; // ������
	private OnShakeListener onShakeListener; // ���ٶȸ�Ӧ������
	private Context mContext; // ������
	private long lastUpdateTime; // �ϴμ��ʱ��
	// �ֻ���һ��λ��ʱ���ٶȸ�Ӧ����
	private float lastX;
	private float lastY;
	private float lastZ;

	public ShakeListener(Context c) {
		// ��ü�������
		mContext = c;
		start();
	}

	public void start() {
		// ��ô�����������
		sensorManager = (SensorManager) mContext
				.getSystemService(Context.SENSOR_SERVICE);
		if (sensorManager != null) {
			// ��ü��ٶȴ�����
			sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		}
		// ע��
		if (sensor != null) {
			sensorManager.registerListener(this, sensor,
					SensorManager.SENSOR_DELAY_GAME);
		}else{
			Toast.makeText(mContext, "�����ֻ���֧�ָù���", 0).show();
		}

	}

	// ���ٶȸ�Ӧ����Ӧ��ñ仯����
	public void onSensorChanged(SensorEvent event) {
		long currentUpdateTime = System.currentTimeMillis();	// ��ǰ���ʱ��
		long timeInterval = currentUpdateTime - lastUpdateTime;		// ���μ���ʱ����
		// �ж��Ƿ�ﵽ�˼��ʱ����
		if (timeInterval < UPTATE_INTERVAL_TIME)
			return;
		// ���ڵ�ʱ����lastʱ��
		lastUpdateTime = currentUpdateTime;

		// ���x,y,z����
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];

		// ���x,y,z�ı仯ֵ
		float deltaX = x - lastX;
		float deltaY = y - lastY;
		float deltaZ = z - lastZ;

		// �����ڵ�������last����
		lastX = x;
		lastY = y;
		lastZ = z;

		double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ
				* deltaZ)
				/ timeInterval * 10000;
		// �ﵽ�ٶȷ�ֵ��������ʾ
		if (speed >= SPEED_SHRESHOLD) {
			onShakeListener.onShake();
		}
	}

	// ҡ�μ����ӿ�
	public interface OnShakeListener {
		public void onShake();
	}

	// ֹͣ���
	public void stop() {
		sensorManager.unregisterListener(this);
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	} // ����������Ӧ������

	public void setOnShakeListener(OnShakeListener listener) {
		onShakeListener = listener;
	}

}