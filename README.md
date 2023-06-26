# micro-sys

micro sys by spring

docker build -t wanjia1/prometheus-demo:1.0 . docker compose up -d

基于k8s 搭建 prometheus/grafana 体系

1. 安装helm
2. 通过helm 安装prometheus operator
3. 发布多个节点 修改prometheus service monitor 通过定义http端点 收集