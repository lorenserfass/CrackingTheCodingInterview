
setwd('~/Documents/CrackingTheCodingInterview/ch9')
df <- read.csv('robots.csv')

library(ggplot2)
theme_set(theme_minimal())



plt <- ggplot(df, aes(m, n, color = function_name)) +
  geom_point(alpha = .5, size = 2) +
  scale_x_continuous(limits = c(0, 50)) +
  scale_color_discrete(name = 'Function') +
  ggtitle("Successful calculations of robot(m, n) up to m + n <= 50",
          "(white space is overflowed calculations)")
plt

png("robot_overflow.png")
print(plt)
dev.off()
