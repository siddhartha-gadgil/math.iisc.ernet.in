---
---

<ul>
{% for paper in site.data.pubs %}
  <li>
    {{ paper.author }}, <em>{{ paper.title }}</em>
  </li>
{% endfor %}
</ul>
