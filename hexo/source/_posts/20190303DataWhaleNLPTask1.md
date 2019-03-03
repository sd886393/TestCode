title: windows下TensorFlow安装与imdb文本分类
date: 2019-03-03
categories: 
- 2019-03
tags: 
 - NLP
---

## windows下进行TensorFlow GPU版本安装(不适用于CPU版本)
基于各种原因，本次的实验环境在windows下进行。
其中本机的配置如下：
```
硬件：
 - CPU：Intel Xeon E3-1505M
 - RAM：64GB
 - GPU：NVIDIA Quadro M2000M
软件：
 - OS：Windows 10 专业工作站 1809 x64
```
正确的步骤如下所示:
```
1. 请务必保证安装的 `Python` 版本为 `3.6.X`, `TensorFlow` 对于 `3.7.X`还未支持完全
2. 安装 Cuda 10.0, 目前不要使用Cuda 10.1的版本,因为还未支持
3. 安装 cudnn for 10.0 
4. 安装 tensorflow-gpu
5. 添加对应的环境变量
SET PATH=C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v10.0\bin;%PATH%
SET PATH=C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v10.0\extras\CUPTI\libx64;%PATH%
SET PATH=C:\tools\cuda\bin;%PATH%
6. 验证(在输入下面的代码并运行后,会出现3min的等待,目前原因不明)
python -c "import tensorflow as tf; tf.enable_eager_execution(); print(tf.reduce_sum(tf.random_normal([1000, 1000])))"
7. 结果
2019-03-03 17:28:53.391788: I tensorflow/core/platform/cpu_feature_guard.cc:141] Your CPU supports instructions that this TensorFlow binary was not compiled to use: AVX2
2019-03-03 17:28:53.569006: I tensorflow/core/common_runtime/gpu/gpu_device.cc:1433] Found device 0 with properties:
name: Quadro M2000M major: 5 minor: 0 memoryClockRate(GHz): 1.137
pciBusID: 0000:01:00.0
totalMemory: 4.00GiB freeMemory: 3.34GiB
2019-03-03 17:28:53.600653: I tensorflow/core/common_runtime/gpu/gpu_device.cc:1512] Adding visible gpu devices: 0
2019-03-03 17:28:54.903988: I tensorflow/core/common_runtime/gpu/gpu_device.cc:984] Device interconnect StreamExecutor with strength 1 edge matrix:
2019-03-03 17:28:54.921578: I tensorflow/core/common_runtime/gpu/gpu_device.cc:990] 0
2019-03-03 17:28:54.932974: I tensorflow/core/common_runtime/gpu/gpu_device.cc:1003] 0: N
2019-03-03 17:28:54.946509: I tensorflow/core/common_runtime/gpu/gpu_device.cc:1115] Created TensorFlow device (/job:localhost/replica:0/task:0/device:GPU:0 with 3048 MB memory) -> physical GPU (device: 0, name: Quadro M2000M, pci bus id: 0000:01:00.0, compute capability: 5.0)
tf.Tensor(1187.2697, shape=(), dtype=float32)
```
## 使用 imdb数据集进行文本分类
首先使用下属的代码下载数据集
```
imdb = keras.datasets.imdb
(train_data, train_labels), (test_data, test_labels) = imdb.load_data(num_words=10000)
```
下载完之后我们可以看到该数据集包含了25000条训练数据和25000条测试数据
```
print("Training entries: {}, labels: {}".format(len(train_data), len(test_data)))
```
我们查看某一条具体的imdb影评是什么样的格式
```
print(train_data[0])
----
[1, 14, 22, 16, 43, 530, 973, 1622, 1385, 65, 458, 4468, 66, 3941, 4, 173, 36, 256, 5, 25, 100, 43, 838, 112, 50, 670, 2, 9, 35, 480, 284, 5, 150, 4, 172, 112, 167, 2, 336, 385, 39, 4, 172, 4536, 1111, 17, 546, 38, 13, 447, 4, 192, 50, 16, 6, 147, 2025, 19, 14, 22, 4, 1920, 4613, 469, 4, 22, 71, 87, 12, 16, 43, 530, 38, 76, 15, 13, 1247, 4, 22, 17, 515, 17, 12, 16, 626, 18, 2, 5, 62, 386, 12, 8, 316, 8, 106, 5, 4, 2223, 5244, 16, 480, 66, 3785, 33, 4, 130, 12, 16, 38, 619, 5, 25, 124, 51, 36, 135, 48, 25, 1415, 33, 6, 22, 12, 215, 28, 77, 52, 5, 14, 407, 16, 82, 2, 8, 4, 107, 117, 5952, 15, 256, 4, 2, 7, 3766, 5, 723, 36, 71, 43, 530, 476, 26, 400, 317, 46, 7, 4, 2, 1029, 13, 104, 88, 4, 381, 15, 297, 98, 32, 2071, 56, 26, 141, 6, 194, 7486, 18, 4, 226, 22, 21, 134, 476, 26, 480, 5, 144, 30, 5535, 18, 51, 36, 28, 224, 92, 25, 104, 4, 226, 65, 16, 38, 1334, 88, 12, 16, 283, 5, 16, 4472, 113, 103, 32, 15, 16, 5345, 19, 178, 32]
```
说明所有的影评根据词典已经转化为相应的数字,我们尝试将对应的数字转换为真实的词
```
# 构建一个词与数字的映射字典
word_index = imdb.get_word_index()
# 创建一个数字与词的映射字典
word_index = {k:(v+3) for k,v in word_index.items()} 
word_index["<PAD>"] = 0
word_index["<START>"] = 1
word_index["<UNK>"] = 2 # unknown
word_index["<UNUSED>"] = 3

index_word = dict([(value, key) for (key, value) in word_index.items()])

def decode_review(text):
    return ' '.join([index_word.get(i, '?') for i in text])
```
现在我们就可以通过`decode_review(train_data[0])`来获得原始的影评了
```
"<START> this film was just brilliant casting location scenery story direction everyone's really suited the part they played and you could just imagine being there robert <UNK> is an amazing actor and now the same being director <UNK> father came from the same scottish island as myself so i loved the fact there was a real connection with this film the witty remarks throughout the film were great it was just brilliant so much that i bought the film as soon as it was released for <UNK> and would recommend it to everyone to watch and the fly fishing was amazing really cried at the end it was so sad and you know what they say if you cry at a film it must have been good and this definitely was also <UNK> to the two little boy's that played the <UNK> of norman and paul they were just brilliant children are often left out of the <UNK> list i think because the stars that play them all grown up are such a big profile for the whole film but these children are amazing and should be praised for what they have done don't you think the whole story was so lovely because it was true and was someone's life after all that was shared with us all"
```
为了更好的进行下一步的神经网络处理,我们将对应的影评数据进行向量化,向量的长度为256
```
train_data = keras.preprocessing.sequence.pad_sequences(train_data,
                                                        value=word_index["<PAD>"],
                                                        padding='post',
                                                        maxlen=256)

test_data = keras.preprocessing.sequence.pad_sequences(test_data,
                                                       value=word_index["<PAD>"],
                                                       padding='post',
                                                       maxlen=256)
```
### 构建模型
```
# 初始化10000词的词典
vocab_size = 10000

model = keras.Sequential()
model.add(keras.layers.Embedding(vocab_size, 16))#每个词的向量长度为16
model.add(keras.layers.GlobalAveragePooling1D())
model.add(keras.layers.Dense(16, activation=tf.nn.relu))
model.add(keras.layers.Dense(1, activation=tf.nn.sigmoid))

model.summary()
```

### 创建优化器
```
# 使用adam算法
model.compile(optimizer='adam',
              loss='binary_crossentropy',
              metrics=['acc'])
# 创建一个验证集
x_val = train_data[:10000]
partial_x_train = train_data[10000:]

y_val = train_labels[:10000]
partial_y_train = train_labels[10000:]
```

### 开始进行训练
```
history = model.fit(partial_x_train,
                    partial_y_train,
                    epochs=40,
                    batch_size=512,
                    validation_data=(x_val, y_val),
                    verbose=1)
```

### 评估对应的结果
```
results = model.evaluate(test_data, test_labels)
print(results)
```

### 使用到的相关的术语
1. ROC(Receiver Operating Characteristic)的操作方式与之前的P-R图类似,并提出了两个概念,真正利率TPR,假正利率FPR
$$
TPR=TP/(TP+FN)
$$
$$
FPR=FP/(FP+TN)
$$
2. ACC 精确度,即正确与全集之比
3. 召回率,即正确的与集合中所有真正正确的数据集之比



## 参考
[使用keras进行文本分类](https://tensorflow.google.cn/tutorials/keras/basic_text_classification)




