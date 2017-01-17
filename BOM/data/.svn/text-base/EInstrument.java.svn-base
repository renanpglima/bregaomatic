package data;

public enum EInstrument {
	Keyboard,
	Guitar,
	Drums,
	Bass,
	Metal,
	Fx;

	public static EInstrument toInstrument(String instrumentName) throws Exception{
		EInstrument ret = Drums;

		if (instrumentName.equals("drums")){
			ret = Drums;
		}
		else if (instrumentName.equals("keyboard")){
			ret = Keyboard;
		}
		else if (instrumentName.equals("bass")){
			ret = Bass;
		}
		else if (instrumentName.equals("guitar")){
			ret = Guitar;
		}
		else if (instrumentName.equals("metal")){
			ret = Metal;
		}
		else if ((instrumentName.equals("fx"))){
			ret = Fx;
		}
		else{
			throw new Exception("O instrumento " + instrumentName + " não existe");
		}
		return ret;
	}

	public static int toInteger(EInstrument instrument) throws Exception{
		int ret = -1;
		switch (instrument) {
		case Keyboard:
			ret = 0;
			break;
		case Guitar:
			ret = 1;
			break;
		case Drums:
			ret = 2;
			break;
		case Bass:
			ret = 3;
			break;
		case Metal:
			ret = 4;
			break;
		case Fx:
			ret = 5;
			break;

		default:
			throw new Exception("Enum não existe");
		}
		return ret;
	}
}
