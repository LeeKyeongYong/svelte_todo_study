<script lang="ts">
	import '../app.pcss';
	const { children } = $props();
</script>

<header>헤더</header>
<main class="flex flex-grow flex-col">{@render children()}</main>
<footer>푸터</footer>