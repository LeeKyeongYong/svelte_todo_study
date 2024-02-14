import toastr from 'toastr';
import 'toastr/build/toastr.css';

toastr.options = {
	showDuration: 300,
	hideDuration: 300,
	timeOut: 3000,
	extendedTimeOut: 1000
};

class Rq {
	public msgInfo(message: string) {
		toastr.info(message);
	}

	public msgError(message: string) {
		toastr.error(message);
	}
}

const rq = new Rq();
export default rq;