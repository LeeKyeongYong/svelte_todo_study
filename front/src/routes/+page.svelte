<script lang="ts">
	import rq from '$lib/https/reqData';
</script>

<button class="btn btn-primary btn-wide" onclick={() => rq.msgInfo('안녕')}>버튼</button>