export interface Todo {
	id: number;
	content: string;
	completed: boolean;
}

export function makeTodo(id: number, content: string, completed: boolean): Todo {
	return { id, content, completed };
}