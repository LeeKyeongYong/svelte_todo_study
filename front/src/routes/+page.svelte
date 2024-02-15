<script lang="ts">
	import toastr from 'toastr';
    import rq from '$lib/https/reqData';
	import 'toastr/build/toastr.css';
</script>

<button class="btn btn-primary btn-wide" onclick={() => rq.msgInfo('안녕')}>버튼</button>
