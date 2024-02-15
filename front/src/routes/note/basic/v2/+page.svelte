<script lang="ts">
	let name = $state('홍길동');
	let age = $state(20);
	const doubleAge = $derived(age * 2);
	$effect(() => console.log('name:', name));
</script>

<h1>페이지</h1>
<!-- svelte-ignore a11y-click-events-have-key-events -->
<!-- svelte-ignore a11y-no-noninteractive-element-interactions -->
<p onclick={() => (name = '홍길순')}>이름: {name}</p>
<!-- svelte-ignore a11y-click-events-have-key-events -->
<!-- svelte-ignore a11y-no-noninteractive-element-interactions -->
<p onclick={() => age++}>나이: {age}</p>
<p>나이 2배: {doubleAge}</p>