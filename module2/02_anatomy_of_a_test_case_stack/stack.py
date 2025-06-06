from typing import Any


class Stack:
    def __init__(self):
        self.items = [] 
    
    def push(self, data: Any) -> None: 
        self.items.append(data) 
    
    def pop(self) -> Any:
        return self.items.pop() 
        
    def peek(self) -> Any:
        return self.items[-1] 
        
    def is_empty(self) -> bool:
        return len(self.items) == 0
