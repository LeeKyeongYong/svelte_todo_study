<script lang="ts">
	import toastr from 'toastr';
	import 'toastr/build/toastr.css';
</script>

<button class="btn btn-primary btn-wide" onclick={() => toastr.info('안녕')}>버튼</button>
