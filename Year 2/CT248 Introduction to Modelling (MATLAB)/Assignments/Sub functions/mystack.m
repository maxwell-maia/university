function [push, pop, peek] = mystack()
    push = @mystack_push;
    pop = @mystack_pop;
    peek = @mystack_peek;
end

function stack = mystack_push(stack, value)
    stack(end+1) = value;
end

function stack = mystack_pop(stack)
    if isempty(stack)
        error('Cannot pop the value of an empty stack.');
    else
        stack(end) = [];
    end
end

function value = mystack_peek(stack)
    if isempty(stack)
        error('Cannot peek the value of an empty stack.');
    else
        value = stack(end);
    end
end