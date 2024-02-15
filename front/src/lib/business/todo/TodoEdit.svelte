<script lang="ts">
	import type { Todo } from './TodoReq.svelte';
	const { todos } = $props<{ todos: Todo[] }>();
	let editingTodo: Todo = $state({ id: 0, content: '', completed: false });
	export function showTodoEditModal(todo: Todo) {
		Object.assign(editingTodo, todo);
		const modal = document.getElementById('todo-edit-modal') as HTMLDialogElement;
		modal.showModal();
		modal.querySelector('input')?.focus();
	}
	function submitEditTodoForm() {
		// 일치하는 todo의 인덱스를 찾습니다.
		const index = todos.findIndex((todo) => todo.id === editingTodo.id);
		// 인덱스가 유효한 경우, 해당 위치의 todo를 수정합니다.
		if (index !== -1) {
			todos[index].content = editingTodo.content;
		}
		hideTodoEditModal();

		}
	function hideTodoEditModal() {
		const modal = document.getElementById('todo-edit-modal') as HTMLDialogElement;
		modal.close();
	}
</script>

<dialog id="todo-edit-modal" class="modal">
	<div class="modal-box">
		<form method="dialog">
			<button class="btn btn-circle btn-ghost btn-sm absolute right-2 top-2">✕</button>
		</form>
		<form class="grid gap-3" on:submit|preventDefault={submitEditTodoForm}>
			<label class="form-control">
				<div class="label">
					<span class="label-text">할일</span>
				</div>
				<input
					type="text"
					placeholder="할일을 입력해주세요."
					class="input input-bordered"
					bind:value={editingTodo.content}
				/>
			</label>
			<div class="grid grid-cols-2 gap-3">
				<button class="btn btn-primary">저장</button>
				<button type="button" class="btn btn-outline" onclick={hideTodoEditModal}>취소</button>
			</div>
		</form>
	</div>

	<form method="dialog" class="modal-backdrop">
		<button></button>
	</form>
</dialog>