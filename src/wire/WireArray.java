package wire;

public class WireArray {
	private WireArray() {}
	
	/**
	 * initial wire array
	 */
	public static Wire[] wireArray(int number) throws WireException {
		return wireArray(number, null);
	}
	
	public static Wire[] wireArray(int number, String name) throws WireException {
		if (number > 0 && number <= Long.SIZE) {
			Wire[] result = new Wire[number];
			if (name != null) {
				for (int i = 0; i < result.length; i++) {
					result[i] = new Wire(name + i);
				}
			}
			setValue(0, result); // initial value
			return result;
		}
		throw new WireException("wire array length is invalid");
	}
	
	/**
	 * set wire array to presentation a integer number
	 * first is LSB, last is MSB
	 */
	// TODO may support BigInteger
	public static void setValue(long value, Wire[] wires) throws WireException {
		if (wires != null) {
			boolean[] bit = longToBit(value, wires.length);
			for (int i = 0; i < bit.length; i++) {
				if (wires[i] == null) {
					wires[i] = new Wire();
				}
				wires[i].setSignal(bit[i]);
			}
			return;
		}
		throw new WireException("wires is null");
	}
	
	/**
	 * convert wire array signals to presentation a integer number
	 * first is LSB, last is MSB
	 */
	// TODO may support BigInteger
	public static long getValue(Wire[] wires) throws WireException {
		if (wires != null) {
			boolean[] bit = new boolean[wires.length];
			for (int i = 0; i < bit.length; i++) {
				bit[i] = wires[i].getSignal();
			}
			return bitToLong(bit);
		}
		throw new WireException("wires is null");
	}
	
	/*
	 * TODO need two complete number to support negative
	 * http://zh.wikipedia.org/zh-tw/%E4%BA%8C%E8%A3%9C%E6%95%B8
	 */
	private static boolean[] longToBit(long value, int num) throws WireException {
		if (num > 0) {
			int valueBit = 0;
			long temp = Math.abs(value);
			while (temp != 0L) {
				temp = temp >> 1;
				valueBit++;
			}
			if (valueBit > num) {
				throw new WireException("value need more bits to representation");
			}
			
			boolean[] result = new boolean[num];
			for (int i = 0; i < num; i++) {
				result[i] = (value & (1L << i)) != 0;
			}
			return result;
		}
		throw new WireException("wire length must bigger then zero");
	}
	
	// TODO need two complete number to support negative
	private static long bitToLong(boolean[] bit) throws WireException {
		if (bit != null && bit.length > 0 && bit.length <= Long.SIZE) { // bit number bigger then long
			long result = 0L;
			for (int i = 0; i < bit.length; i++) {
				if (bit[i]) {
					result = (result | (1L << i));
				}
			}
			return result;
		}
		throw new WireException("long cannot represent value");
	}
}
