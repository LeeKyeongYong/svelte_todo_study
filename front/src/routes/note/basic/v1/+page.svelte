<script lang="ts">
	let name = '홍길동';
	let age = 20;
	$: doubleAge = age * 2;
	$: console.log('name:', name);
</script>

<h1>페이지</h1>
<!-- svelte-ignore a11y-click-events-have-key-events -->
<!-- svelte-ignore a11y-no-noninteractive-element-interactions -->
<p onclick={() => (name = '홍길순')}>이름: {name}</p>
<!-- svelte-ignore a11y-click-events-have-key-events -->
<!-- svelte-ignore a11y-no-noninteractive-element-interactions -->
<p onclick={() => age++}>나이: {age}</p>
<p>나이 2배: {doubleAge}</p>
 15 changes: 15 additions & 0 deletions15  