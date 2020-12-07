package kapistelykirjasto.util;

public class Result<E, V> {
	private E error;
	private V value;
	private boolean isError;
	
	public boolean isError() {
		return isError;
	}
	
	public boolean isValue() {
		return !isError();
	}
	
	public E getError() {
		return error;
	}
	
	public V getValue() {
		return value;
	}
	
	private Result(E error, V value) {
		if (value != null) {
			this.isError = false;
			this.value = value;
		} else {
			this.isError = true;
			this.error = error;
		}
	}
	
	public static <E,V> Result<E,V> value(V value) {
		return new Result<E, V>(null, value);
	}
	
	public static <E,V> Result<E,V> error(E error) {
		return new Result<E, V>(error, null);
	}
}
