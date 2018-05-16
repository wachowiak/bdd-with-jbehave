Meta:

Narrative:
As a user
I want to use functions of my web calculator
So that I can achieve correct calculation results

Scenario: Basic arithmetic operations
Given a running system
When I call add on 3 and 2, then 5 will be returned
When I call sub on 3 and 2, then 1 will be returned
When I call mul on 3 and 2, then 6 will be returned
When I call div on 3 and 2, then 1 will be returned
When I call div by 0 on 3, then HTTP 400 will be returned