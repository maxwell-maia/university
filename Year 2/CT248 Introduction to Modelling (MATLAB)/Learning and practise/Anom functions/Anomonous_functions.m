f = @(x,a,b,c) a*x.^2+b*x+c;

x = -5000:5000;
y = f(x, 2, 4, 10);
plot(x,y);