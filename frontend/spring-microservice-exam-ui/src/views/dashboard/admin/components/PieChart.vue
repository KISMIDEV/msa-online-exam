<template>
  <div :class="className" :style="{height:height,width:width}"/>
</template>

<script>
import echarts from 'echarts' // echarts theme
import { debounce } from '@/utils'
require('echarts/theme/macarons')

export default {
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '350px'
    }
  },
  data () {
    return {
      chart: null
    }
  },
  mounted () {
    this.initChart()
    this.__resizeHandler = debounce(() => {
      if (this.chart) {
        this.chart.resize()
      }
    }, 100)
    window.addEventListener('resize', this.__resizeHandler)
  },
  beforeDestroy () {
    if (!this.chart) {
      return
    }
    window.removeEventListener('resize', this.__resizeHandler)
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart () {
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          bottom: '10',
          data: ['베트남', '태국', '수단', '몽골', '라오스', '한국', '카자흐스탄', '러시아', '아제르바이잔', '아프가니스탄']
        },
        calculable: true,
        series: [
          {
            name: '수험생분포도',
            type: 'pie',
            roseType: 'radius',
            radius: [15, 95],
            center: ['50%', '38%'],
            data: [
              { value: 1, name: '베트남' },
              { value: 3, name: '태국' },
              { value: 1, name: '수단' },
              { value: 1, name: '몽골' },
              { value: 3, name: '라오스' },
              { value: 1, name: '한국' },
              { value: 3, name: '카자흐스탄' },
              { value: 5, name: '러시아' },
              { value: 1, name: '아제르바이잔' },
              { value: 1, name: '아프가니스탄' }
            ],
            animationEasing: 'cubicInOut',
            animationDuration: 2600,
            label: {
              normal: {
                formatter: function (param) {
                  return param.name + '\n' + Math.round(param.percent) + '%'
                }
              }
            }
          }
        ]
      })
    }
  }
}
</script>
